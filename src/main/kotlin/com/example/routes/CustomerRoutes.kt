package com.example.routes

import com.example.models.customer.Customer
import com.example.models.customer.CustomerManager
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.customerRouting() {
    route("/customer") {
        implementGET()
        implementPOST()
        implementDELETE()
    }
}

private fun Route.implementGET(/* --> /customer <-- */) {
    get {
        if (CustomerManager.isNotEmpty()) {
            call.respond(CustomerManager.all())
        } else {
            call.respondText("get{}, No customer fond", status = HttpStatusCode.OK)
        }
    }

    get("{id}") {
        val id = call.parameters["id"]
        val customer = CustomerManager.id(id!!) ?: return@get call.respondText(
            "No customer with $id",
            status = HttpStatusCode.NotFound //404 Not found
        )

        call.respond(customer)
    }
}

private fun Route.implementPOST(/* --> /customer <-- */) {
    post {
        val customer = call.receive<Customer>()
        CustomerManager.add(customer)
        call.respondText("Customer stored success", status = HttpStatusCode.Created)
    }
}

private fun Route.implementDELETE(/* --> /customer <-- */) {
    delete("{id?}") {
        val id = call.parameters["id"] ?: return@delete call.respondText(
            "Misiing id",
            status = HttpStatusCode.BadRequest
        )

        if (CustomerManager.rm(id)) {
            call.respondText("Customer delete success", status = HttpStatusCode.Accepted)
        } else {
            call.respondText("Not fond", status = HttpStatusCode.NotFound)
        }
    }
}

