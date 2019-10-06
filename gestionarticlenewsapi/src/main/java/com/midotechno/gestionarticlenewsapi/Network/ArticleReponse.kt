package com.midotechno.gestionarticlenewsapi.Network

import com.midotechno.gestionarticlenewsapi.Entities.Article

data class ArticleReponse (
    val status : String ,
    val result : Int ,
    val articles : List<Article>
)