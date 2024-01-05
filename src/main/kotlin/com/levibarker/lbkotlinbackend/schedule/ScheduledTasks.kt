package com.levibarker.lbkotlinbackend.schedule

import com.levibarker.lbkotlinbackend.services.OpenAIService
import kotlinx.coroutines.runBlocking
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import kotlin.time.measureTime


@Component
class ScheduledTasks(private val openAIService: OpenAIService) {
    private val log: Logger = LoggerFactory.getLogger(ScheduledTasks::class.java)

    @Scheduled(
        cron = "59 59 11 * * *",
        zone = "America/Chicago"
    )
    fun generateAIArticle() = runBlocking {
        log.info("Starting AI article generation, this can take up to 30 seconds...")

        val time = measureTime {
            openAIService.generateArticle()
        }

        log.info("AI article generated in $time")
    }
}