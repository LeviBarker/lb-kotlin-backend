package com.levibarker.lbkotlinbackend.controllers

import com.levibarker.lbkotlinbackend.services.WeatherService
import kotlinx.coroutines.runBlocking
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/weather")
@CrossOrigin
class WeatherControllerV1(
    private val weatherService: WeatherService,
) {

    @GetMapping()
    fun getDaytimeWeather(
        @RequestParam("lat") latitude: Double,
        @RequestParam("long") longitude: Double,
    ): ResponseEntity<Any> = runBlocking {
        try {
            val daytimeWeather = weatherService.getDaytimeWeather(latitude, longitude)
            return@runBlocking ResponseEntity
                .ok()
                .body(daytimeWeather)
        } catch (e: Exception) {
            return@runBlocking ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

}