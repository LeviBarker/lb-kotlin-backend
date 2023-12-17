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
     * @param count The number of users to generate. A default number of users
     *  should be created if no value is passed int
     * @return A list of [User] objects.
     */
    fun generateSome(count: Int?): List<User>
}

@Service
class DefaultUserService : UserService {
    override fun generateSome(count: Int?): List<User> {
        return (1..(count ?: 10)).map {
            User(firstName = "$it-first", lastName = "$it-last")
        }
    }
}