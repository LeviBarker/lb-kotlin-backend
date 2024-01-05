package com.levibarker.lbkotlinbackend.controllers

import com.levibarker.lbkotlinbackend.models.User
import com.levibarker.lbkotlinbackend.persistence.entities.UserEntity
import com.levibarker.lbkotlinbackend.persistence.repositories.UserRepository
import com.levibarker.lbkotlinbackend.services.OpenAIService
import com.levibarker.lbkotlinbackend.services.UserService
import kotlinx.coroutines.runBlocking
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/fake")
@CrossOrigin
class FakeControllerV1(
    private val userService: UserService,
    private val openAIService: OpenAIService,
    private val userRepository: UserRepository,
) {

    @GetMapping("/ai")
    fun getOpenAIResponse(@RequestParam("prompt") prompt: String): ResponseEntity<Any> = runBlocking {
        val result = openAIService.chat(prompt)

        return@runBlocking ResponseEntity.ok().body(result)
    }

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
    fun getUsers(@RequestParam("count") count: Int = 10): ResponseEntity<List<User>> {
        return ResponseEntity(userService.generateSome(count), HttpStatus.OK)
    }

    @PostMapping("/users")
    fun createUser(@RequestBody user: UserEntity): ResponseEntity<UserEntity> {
        val savedUser = userRepository.save(user)
        return ResponseEntity(savedUser, HttpStatus.OK)
    }
}