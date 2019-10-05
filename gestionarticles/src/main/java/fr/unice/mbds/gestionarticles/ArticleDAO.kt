package fr.unice.mbds.gestionarticles

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.ArrayList


@Dao
interface ArticleDAO {

    @Query("SELECT * FROM article")
    fun allArticles(): LiveData<List<Article>>

    @Query("SELECT * FROM article WHERE uid IN (:articleIds)")
    fun loadAllByIds(articleIds: IntArray): List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: Article)

    @Delete
    fun delete(article: Article)


    @Query("DELETE FROM article")
    abstract fun deleteAll()
}
