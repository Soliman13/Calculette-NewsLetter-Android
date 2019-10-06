package com.midotechno.gestionarticlenewsapi.Entities

import com.midotechno.gestionarticlenewsapi.R
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

data class Article(
    var title:String,
    var description:String ,
    var urlToImage : String ,   // lien vers l'image url
    var publishedAt : String  , // date publication
    var url : String )  // url de plus d'info sur l'article