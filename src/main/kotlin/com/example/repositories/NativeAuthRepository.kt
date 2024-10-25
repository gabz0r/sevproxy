package com.example.repositories

import com.example.factory.HttpClientFactory
import com.example.models.AuthModel
import com.example.models.NativeAuthResponseModel
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.http.HttpStatusCode

object NativeAuthRepository {
    suspend fun doLoginToSevdesk(credential: AuthModel): NativeAuthResponseModel? {
        val response = HttpClientFactory.createClient().use {
            it.post("https://my.sevdesk.de/auth/mobileauthenticate") {
                url {
                    parameters.append("username_login", credential.email)
                    parameters.append("password_login", credential.password)
                    parameters.append("is_mobile", "true")
                }
            }
        }

        if(response.status == HttpStatusCode.OK) {
            return response.body<NativeAuthResponseModel>()
        }

        return null
    }
}