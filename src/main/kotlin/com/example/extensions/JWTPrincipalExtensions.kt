package com.example.extensions

import io.ktor.server.auth.jwt.JWTPrincipal

fun JWTPrincipal.sevToken() = payload.getClaim("sevToken").asString()
fun JWTPrincipal.sevCft() = payload.getClaim("sevCft").asString()

