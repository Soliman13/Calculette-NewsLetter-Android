package com.midotechno.gestionarticlenewsapi.Network

import com.midotechno.gestionarticlenewsapi.Entities.Article

// format que je vais recupérer depuis json
data class ArticleReponse (
    val status : String ,
    val totalResults : Int ,
    val articles : List<Article>
)