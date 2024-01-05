package com.levibarker.lbkotlinbackend.controllers

import com.levibarker.lbkotlinbackend.persistence.repositories.ArticleRepository
import kotlinx.coroutines.runBlocking
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/articles")
@CrossOrigin
class ArticleControllerV1(
    private val articleRepository: ArticleRepository,
) {

    companion object {
        private const val ARTICLE_ID = 1
    }

    @GetMapping()
    fun getArticleForToday(): ResponseEntity<Any> = runBlocking {
        val article = articleRepository.getReferenceById(ARTICLE_ID)

        return@runBlocking ResponseEntity.ok().body(
            mapOf(
                "main" to article.main,
                "fact" to article.funFact
            )
        )
    }
}