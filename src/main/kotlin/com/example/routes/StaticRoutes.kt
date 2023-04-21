package com.example.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.staticRouties() {

    val mediaDir = System.getenv("MEDIA_DIR")

    mediaDir?.let {
        static("video") {
            files("$it/video")
        }
        static("image") {
            files("$it/image")
        }
    } ?: intercept(ApplicationCallPipeline.Plugins) { //todo: 待优化
        call.respond(HttpStatusCode.NotFound)
    }
}