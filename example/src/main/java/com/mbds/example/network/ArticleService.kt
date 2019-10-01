package com.mbds.example.network

import com.mbds.example.adapters.Article
import retrofit2.Call
import retrofit2.http.GET

interface ArticleService {

    interface ArticleService {
        @GET("sources?apiKey=e17db2b2e3784f6ca5ef1324ab7bee4f")
        fun list(): Call<List<Article>>
    }
}