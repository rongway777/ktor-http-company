package com.example.routes

import com.example.models.artical.ArticalManager
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

//返回的都是xxx.ftl，动态网页
fun Route.articalRouting() {
    route("articales") {

        // get all articales
        // http://localhost:port/articales

        get {
            call.respond(FreeMarkerContent("index.ftl", mapOf("articales" to ArticalManager.all())))
        }

        // show html to new artical
        // http://localhost:port/articales/new
        get("new") {
            call.respond(FreeMarkerContent("new.ftl", null))
        }

        //save an artical
        post {
            val formParams = call.receiveParameters() //获取表单post上来的数据
            val title = formParams.getOrFail("title")
            val body = formParams.getOrFail("body")
            val id = ArticalManager.add(ArticalManager.createArtical(title, body))
            //将页面重定向到文章页面
            call.respondRedirect("/articales/${id}")
        }

        //get artical by id
        get("{id}") {
            val id = call.parameters["id"]?.toInt()
            val article = ArticalManager.id(id!!)
            call.respond(FreeMarkerContent("show.ftl", mapOf("article" to article)))
        }

        //edit a artical by id
        get("{id}/edit") {
            val id = call.parameters["id"]?.toInt()
            val article = ArticalManager.id(id!!)
            call.respond(FreeMarkerContent("edit.ftl", mapOf("article" to article)))
        }

        //delete a artical by id
        delete("{id}/delete") {

        }


        get("test") {
            val size = ArticalManager.addTestInfo()
            call.respondText("added size: $size")
        }
    }
}