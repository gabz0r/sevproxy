package com.example.repositories

import com.example.extensions.sevCft
import com.example.extensions.sevToken
import com.example.factory.HttpClientFactory
import com.example.models.AuthModel
import com.example.models.NativeAuthResponseModel
import com.example.models.NativeContactModel
import com.example.models.NativeWrapperResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.jwt.JWTPrincipal

object NativeContactsRepository {
    suspend fun getContacts(principal: JWTPrincipal): NativeWrapperResponse<NativeContactModel>? {
        val response = HttpClientFactory.createClient().use {
            it.get("https://my.sevdesk.de/api/v1/Contact") {
                url {
                    parameters.append("token", principal.sevToken())
                    parameters.append("cft", principal.sevCft())
                }
            }
        }

        if(response.status == HttpStatusCode.OK) {
            return response.body<NativeWrapperResponse<NativeContactModel>>()
        }

        return null
    }
}