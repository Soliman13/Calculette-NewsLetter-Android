package fr.unice.mbds.gestionarticles

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import android.app.Application


class ArticleViewModel(application:Application): AndroidViewModel(application){
    private val aRepository: ArticleRepository
    internal val allArticles : LiveData<List<Article>>


    init {
        aRepository = ArticleRepository(application)
        allArticles = aRepository.allArticles
    }

    fun insert(article:Article){
        aRepository.insert(article)
    }
}