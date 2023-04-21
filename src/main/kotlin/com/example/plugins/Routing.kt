package com.example.plugins

import com.example.routes.customerRouting
import com.example.routes.defaultHtml
import com.example.routes.staticRouties
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
        customerRouting()
    }
}