package com.levibarker.lbkotlinbackend.models

import kotlinx.serialization.Serializable

@Serializable
data class NWSForecastPeriod(
    val isDaytime: Boolean,
    val temperature: Double,
    val temperatureUnit: String,
    val shortForecast: String,
)
