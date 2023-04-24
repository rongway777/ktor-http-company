package com.example.routes

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*


/**
 * 提供静态内容
 */
fun Route.staticRouting() {
    static("/static") {
        resources("files")
    }
}