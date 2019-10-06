package com.midotechno.gestionarticlenewsapi.Network

import retrofit2.Call
import retrofit2.http.GET

interface ArticleService {

    @GET("top-headlines?country=us&apiKey=53006b6b094544f4833ce5d0ee66e18b")
    fun list(): Call<ArticleReponse>

}