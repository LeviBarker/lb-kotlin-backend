package com.levibarker.lbkotlinbackend

import com.levibarker.lbkotlinbackend.services.OpenAIService
import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class LbKotlinBackendApplication

fun main(args: Array<String>) {
    val context = runApplication<LbKotlinBackendApplication>(*args)

    generateArticleOnStartup(context.getBean(OpenAIService::class.java))
}

fun generateArticleOnStartup(openAIService: OpenAIService) = runBlocking {
    openAIService.generateArticle()
}
