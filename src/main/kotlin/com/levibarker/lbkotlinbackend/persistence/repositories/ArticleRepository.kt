package com.levibarker.lbkotlinbackend.persistence.repositories;

import com.levibarker.lbkotlinbackend.persistence.entities.ArticleEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository : JpaRepository<ArticleEntity, Int> {
}