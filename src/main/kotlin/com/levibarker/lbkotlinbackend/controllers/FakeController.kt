package com.levibarker.lbkotlinbackend.controllers

import com.levibarker.lbkotlinbackend.UserService
import com.levibarker.lbkotlinbackend.models.User
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("fake")
@CrossOrigin
class FakeController(private val userService: UserService) {

    @GetMapping("/ok")
    fun getOkResponse(): ResponseEntity<Any> {
        return ResponseEntity
            .ok()
            .body(mapOf("message" to "OK"))
    }

    @GetMapping("/bad-request")
    fun getBadRequestResponse(): ResponseEntity<Any> {
        return ResponseEntity
            .badRequest()
            .body(mapOf("message" to "bad request"))
    }

    @GetMapping("/internal-server-error")
    fun getInternalServerError(): ResponseEntity<Any> {
        return ResponseEntity
            .internalServerError()
            .body(mapOf("message" to "internal server error"))
    }

    @GetMapping("/users")
    fun getUsers(@RequestParam("count") count: Int = 10): ResponseEntity<Any> {
        return ResponseEntity
            .ok()
            .body(userService.generateSome(count))
    }
}