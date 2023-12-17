package com.levibarker.lbkotlinbackend.models

import java.util.UUID

data class User(
    val id: UUID = UUID.randomUUID(),
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val phone: String? = null
)
