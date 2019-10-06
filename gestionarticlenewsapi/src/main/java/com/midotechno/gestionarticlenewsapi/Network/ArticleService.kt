package com.midotechno.gestionarticlenewsapi.Network

import retrofit2.Call
import retrofit2.http.GET

interface ArticleService {

    // All articles about Bitcoin from the last month, sorted by recent first
    @GET("everything?q=bitcoin&from=2019-09-06&sortBy=publishedAt&apiKey=ea0de83e47864485b1514477f0ea732f")
    fun list(): Call<ArticleReponse>

}