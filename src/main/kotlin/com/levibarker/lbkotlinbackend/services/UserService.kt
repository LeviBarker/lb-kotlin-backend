package com.levibarker.lbkotlinbackend.services

import com.levibarker.lbkotlinbackend.models.User
import org.springframework.stereotype.Service

/**
 * Defines methods for generating a list of users.
 */
interface UserService {

    /**
     * Generates a list of [User] objects.
     *
     * @param count The number of users to generate.
     * @return A list of [User] objects.
     */
    fun generateSome(count: Int): List<User>

    /**
     * Generates a list of [User] objects with default count of 10.
     *
     * @return A list of [User] objects.
     */
    fun generateSome(): List<User>
}

@Service
class DefaultUserService : UserService {
    override fun generateSome(count: Int): List<User> {
        return (1..count).map {
            User(firstName = "$it-first", lastName = "$it-last")
        }
    }

    override fun generateSome(): List<User> {
        return generateSome(10)
    }
}