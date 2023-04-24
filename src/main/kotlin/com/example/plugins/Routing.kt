package com.example.plugins

import com.example.routes.articalRouting
import com.example.routes.customerRouting
import com.example.routes.staticRouting
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
        customerRouting()
        staticRouting()
        articalRouting()
    }

}