package com.example.routes

import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Route.defaultHtml() {
    static("/") {
        default("index.html")
    }
}