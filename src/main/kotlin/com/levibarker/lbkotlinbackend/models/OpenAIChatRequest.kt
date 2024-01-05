package com.levibarker.lbkotlinbackend.models

import kotlinx.serialization.Serializable

@Serializable
data class OpenAIChatMessage(
    val role: String,
    val content: String,
)

@Serializable
data class OpenAIChatRequest(
    val model: String,
    val messages: List<OpenAIChatMessage>,
    val temperature: Double,
)
