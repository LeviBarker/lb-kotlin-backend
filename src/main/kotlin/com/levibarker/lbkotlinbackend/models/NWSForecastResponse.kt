package com.levibarker.lbkotlinbackend.models

import kotlinx.serialization.Serializable

@Serializable
data class NWSForecastResponse(
    val properties: NWSForecastProperties,
)
