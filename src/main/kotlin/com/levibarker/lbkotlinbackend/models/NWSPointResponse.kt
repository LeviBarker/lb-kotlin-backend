package com.levibarker.lbkotlinbackend.models

import kotlinx.serialization.Serializable

@Serializable
data class NWSPointProperties(
    val forecast: String,
)

@Serializable
data class NWSPointResponse(
    val properties: NWSPointProperties,
)
