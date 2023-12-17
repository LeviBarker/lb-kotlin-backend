package com.levibarker.lbkotlinbackend

import com.levibarker.lbkotlinbackend.models.User
import org.springframework.stereotype.Service

@Service
class UserService {
    fun generateSome(count: Int = 10): List<User> {
        return (1..count).map {
            User(firstName = "$it-first", lastName = "$it-last")
        }
    }
}