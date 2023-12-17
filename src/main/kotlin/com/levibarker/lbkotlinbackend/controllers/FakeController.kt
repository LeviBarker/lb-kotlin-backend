package com.levibarker.lbkotlinbackend.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("fake")
class FakeController {

    @GetMapping("/ok")
    fun getOkResponse(): ResponseEntity<Any> {
        return ResponseEntity.ok().build()
    }

    @GetMapping("/bad-request")
    fun getBadRequestResponse(): ResponseEntity<Any> {
        return ResponseEntity.badRequest().build()
    }
}