package com.example.controllers

import com.example.controllers.v1.auth
import com.example.controllers.v1.contacts
import io.ktor.server.auth.authenticate
import io.ktor.server.routing.Route
import io.ktor.server.routing.route

fun Route.v1() = route("v1") {
    auth()
    authenticate {
        contacts()
    }
}