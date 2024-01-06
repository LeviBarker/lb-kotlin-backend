package com.levibarker.lbkotlinbackend.services

import com.levibarker.lbkotlinbackend.models.OpenAIChatMessage
import com.levibarker.lbkotlinbackend.models.OpenAIChatRequest
import com.levibarker.lbkotlinbackend.models.OpenAIChatResponse
import com.levibarker.lbkotlinbackend.persistence.entities.ArticleEntity
import com.levibarker.lbkotlinbackend.persistence.repositories.ArticleRepository
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.java.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Service

interface OpenAIService {

    suspend fun generateArticle(): ArticleEntity
    suspend fun chat(message: String): String?
}

@Service
class HTTPOpenAIService(private val articleRepository: ArticleRepository) : OpenAIService {

    companion object {
        const val OPENAI_MODEL = "gpt-3.5-turbo-0613"
    }

    private final val http = HttpClient(Java) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 30_000
        }

        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = "api.openai.com"
            }
            contentType(ContentType.Application.Json)
            bearerAuth(System.getenv("OPENAI_API_KEY"))
        }
    }

    override suspend fun generateArticle(): ArticleEntity = coroutineScope {
        val mainJob =
            async { chat("newspaper story for kids with an <h1> tag for the headline and <p> tags for the story limit prose, choose from sports, science, art, or superheros") }
        val funFactJob = async { chat("science fact for 7 year old limit prose limit 500 characters") }

        val article = ArticleEntity()
        article.id = 1 // Static ID
        article.main = mainJob.await()
            ?.replace("\n", "")
            ?.replace("\\", "")
        article.funFact = funFactJob.await()

        articleRepository.save(article)
        return@coroutineScope article
    }

    override suspend fun chat(message: String): String? {
        val response = this.http.post("v1/chat/completions") {
            setBody(
                OpenAIChatRequest(
                    model = OPENAI_MODEL,
                    messages = listOf(
                        OpenAIChatMessage(
                            role = "user",
                            content = message
                        )
                    ),
                    temperature = 0.7
                )
            )
        }.body<OpenAIChatResponse>();

        return response.choices?.firstOrNull()?.message?.content;
    }


}