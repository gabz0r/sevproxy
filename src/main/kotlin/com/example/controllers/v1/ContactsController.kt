package com.example.controllers.v1

import com.example.models.AuthModel
import com.example.models.AuthResponse
import com.example.models.ContactModel
import com.example.repositories.NativeContactsRepository
import io.github.smiley4.ktorswaggerui.data.ExampleDescriptor
import io.github.smiley4.ktorswaggerui.dsl.routing.get
import io.ktor.http.HttpStatusCode
import io.ktor.http.headers
import io.ktor.http.headersOf
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.route

fun Route.contacts() = route("contacts") {
    get({
        description = "Returns all contacts to an authenticated user"
        request {
            headerParameter<String>("Authorization") {
                description = "Bearer-prefixed JSON web token (JWT)"
            }
        }
        response {
            HttpStatusCode.OK to {
                description = "successful request - contacts are returned"
                body<List<ContactModel>> {
                }
            }
            HttpStatusCode.BadRequest to {
                description = "catches all failure scenarios"
            }
        }
    }) {
        call.principal<JWTPrincipal>()?.let {
            val contacts = NativeContactsRepository.getContacts(
                it
            )
            contacts?.run {
                val mapped = contacts.objects.map { native -> ContactModel.fromNativeModel(native) }
                call.respond(mapped)
            } ?: run {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}