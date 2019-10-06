package com.midotechno.gestionarticlenewsapi.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ArticlePository {
    private val service: ArticleService

    init {
        val retrofit = Retrofit.Builder().apply {
            baseUrl("https://newsapi.org/v2/").
                addConverterFactory(GsonConverterFactory.create())
        }.build()
        service = retrofit.create(ArticleService::class.java)
    }




    fun list(): ArticleReponse {
        val response =  service.list().execute()
        return response.body() ?:  ArticleReponse("Nok",0, emptyList())
    }
}