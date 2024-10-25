package com.example.plugins

import com.example.controllers.v1
import io.github.smiley4.ktorswaggerui.SwaggerUI
import io.github.smiley4.ktorswaggerui.routing.openApiSpec
import io.github.smiley4.ktorswaggerui.routing.swaggerUI
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.routing.*
import io.ktor.server.webjars.Webjars

fun Application.configureRouting() {
    install(Webjars) {
        path = "/webjars" //defaults to /webjars
    }

    install(SwaggerUI) {
        info {
            title = "sevdesk mobile proxy demo"
            version = "latest"
        }
        server {
            url = "http://localhost:8080"
            description = "Development Server"
        }
    }

    routing {
        route("api") {
            v1()
        }

        route("sev_mobile_proxy.json") {
            openApiSpec()
        }
        route("swagger-ui") {
            swaggerUI("/sev_mobile_proxy.json")
        }
    }
}
