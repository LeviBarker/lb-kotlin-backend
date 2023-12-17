package com.levibarker.lbkotlinbackend.services

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DefaultUserServiceTest {

    @Test
    fun `should generate 10 users by default`() {
        val users = userService.generateSome()

        assert(users.size == 10)
    }

    @Test
    fun `should generate 99 users by param`() {
        val users = userService.generateSome(99)

        assert(users.size == 99)
    }

    @Test
    fun `should not generate users with zero input`() {
        val users = userService.generateSome(0)

        assert(users.isEmpty())
    }

    @Test
    fun `should not generate users with negative input`() {
        val users = userService.generateSome(-1)

        assert(users.isEmpty())
    }

    companion object {

        private lateinit var userService: UserService

        @JvmStatic
        @BeforeAll
        fun setup() {
            userService = DefaultUserService()
        }
    }
}