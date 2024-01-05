package com.levibarker.lbkotlinbackend.models

import kotlinx.serialization.Serializable


@Serializable
data class OpenAIChatChoiceMessage(
    val content: String,
)

@Serializable
data class OpenAIChatChoice(
    val message: OpenAIChatChoiceMessage,
)

@Serializable
data class OpenAIChatResponse(
    val choices: List<OpenAIChatChoice>?,
)
