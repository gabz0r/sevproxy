package com.example.controllers.v1

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.models.AuthModel
import com.example.models.NativeAuthResponseModel
import com.example.plugins.jwtAudience
import com.example.plugins.jwtDomain
import com.example.plugins.jwtSecret
import com.example.repositories.NativeAuthRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import java.util.Date

fun Route.auth() = route("auth") {
    get("login") {
        val authModel = call.receive<AuthModel>()

        val authRes = NativeAuthRepository.doLoginToSevdesk(authModel)

        authRes?.run {
            val token = JWT.create()
                .withAudience(jwtAudience)
                .withIssuer(jwtDomain)
                .withClaim("username", authModel.email)
                .withClaim("userId", authRes.user.userId)
                .withClaim("clientId", authRes.user.clientId)
                .withClaim("sevToken", authRes.user.apiToken)
                .withClaim("sevCft", authRes.user.cft)
                .withExpiresAt(Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)))
                .sign(Algorithm.HMAC256(jwtSecret))

            call.respond(hashMapOf("token" to token))
        } ?: run {
            call.respond(HttpStatusCode.Unauthorized)
        }

        call.respond(HttpStatusCode.Unauthorized)
    }
}