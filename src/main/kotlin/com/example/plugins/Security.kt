package com.example.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

const val jwtAudience = "mobile-audience"
const val jwtDomain = "https://sevdesk.de/"
const val jwtRealm = "sevdesk.proxy.mobile"
const val jwtSecret = "tobeornottobe"

fun Application.configureSecurity() {
    authentication {
        jwt {
            realm = jwtRealm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(jwtSecret))
                    .withAudience(jwtAudience)
                    .withIssuer(jwtDomain)
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(jwtAudience))
                    JWTPrincipal(credential.payload) else null
            }
        }
    }
}
