package com.levibarker.lbkotlinbackend.services

import com.levibarker.lbkotlinbackend.models.NWSForecastPeriod
import com.levibarker.lbkotlinbackend.models.NWSForecastResponse
import com.levibarker.lbkotlinbackend.models.NWSPointResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.java.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Service

interface WeatherService {
    suspend fun getDaytimeWeather(latitude: Double, longitude: Double): NWSForecastPeriod?
}

@Service
class HttpWeatherService : WeatherService {

    private final val http = HttpClient(Java) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }

        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = "api.weather.gov"
            }
        }
    }

    override suspend fun getDaytimeWeather(latitude: Double, longitude: Double): NWSForecastPeriod? {
        // Get forecast office and lookup coordinate forecast
        val coordinateInfo: NWSPointResponse = this.http.get("points/$latitude,$longitude").body()
        val forecast: NWSForecastResponse = this.http.get(coordinateInfo.properties.forecast).body()

        // Get next daytime forecast period
        return forecast.properties.periods.firstOrNull { it.isDaytime }
    }

}