package com.example.controllers.v1

import com.example.models.ContactModel
import com.example.repositories.NativeContactsRepository
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Route.contacts() = route("contacts") {
    get("test") {
        call.principal<JWTPrincipal>()?.let {
            val contacts = NativeContactsRepository.getContacts(
                it
            )
            contacts?.run {
                val mapped = contacts.objects.map { native -> ContactModel.fromNativeModel(native) }
                call.respond(mapped)
            } ?: run {

            }
        }
    }
}