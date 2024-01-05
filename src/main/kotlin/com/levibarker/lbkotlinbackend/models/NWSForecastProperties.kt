package com.levibarker.lbkotlinbackend.models

import kotlinx.serialization.Serializable

@Serializable
data class NWSForecastProperties(
    val periods: List<NWSForecastPeriod>,
)
