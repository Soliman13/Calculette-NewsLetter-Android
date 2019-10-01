package com.mbds.example.network.repository

import com.mbds.example.adapters.Article
import com.mbds.example.network.ArticleService
import retrofit2.Retrofit

class ArticleRepository {

    private val service: ArticleService.ArticleService

    init {
        val retrofit = Retrofit.Builder().apply {
            baseUrl("https://newsapi.org/v2/")}.build()
        service = retrofit.create(ArticleService.ArticleService::class.java)}

    fun list(): List<Article> {
        val response =  service.list().execute()
        return response.body() ?: emptyList()
    }
}