package com.example.routes

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*


/**
 * 提供静态内容
 */
fun Route.staticRouting() {

    static("/") {
        defaultResource("index.html") //可以设置一张默认的图片
        //resources("files") // /aa.png --> resources/files/aa.png
    }

    static("/static") {
        resources("files")
    }
}