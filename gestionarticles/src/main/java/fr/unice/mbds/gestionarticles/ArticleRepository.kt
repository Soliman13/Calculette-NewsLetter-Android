package fr.unice.mbds.gestionarticles

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData


internal class ArticleRepository
    (application: Application) {

    private val aDao: ArticleDAO
    val allArticles: LiveData<List<Article>>

    init {
        val db = AppDatabase.getDatabase(application)
        aDao = db!!.articleDao()
        allArticles = aDao.allArticles()
    }

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    fun insert(article: Article) {
        insertAsyncTask(aDao).execute(article)
    }

    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: ArticleDAO) :
        AsyncTask<Article, Void, Void>() {

        override fun doInBackground(vararg params: Article): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }
}