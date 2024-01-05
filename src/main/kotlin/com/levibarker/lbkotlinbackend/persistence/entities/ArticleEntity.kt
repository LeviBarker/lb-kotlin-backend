package com.levibarker.lbkotlinbackend.persistence.entities

import jakarta.persistence.*

@Entity
@Table(name = "article")
open class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @Column(name = "temperature")
    open var temperature: Double? = null

    @Column(name = "weather")
    open var weather: String? = null

    @Column(name = "fun_fact", length = 500)
    open var funFact: String? = null

    @Lob
    @Column(name = "main")
    open var main: String? = null
}