package com.midotechno.gestionarticlenewsapi.Entities

import com.midotechno.gestionarticlenewsapi.R
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

data class Article(
    var title:String,
    var description:String ,
    var urlToImage : String ,
    var publishedAt : String  ,
    var url : String )