package fr.unice.mbds.gestionarticles

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Article::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDAO

    private class PopulateDbAsync internal constructor(db: AppDatabase) :
        AsyncTask<Void, Void, Void>() {

        private val aDao: ArticleDAO

        init {
            aDao = db.articleDao()
        }

        override fun doInBackground(vararg params: Void): Void? {

            aDao.deleteAll();

            var article = Article("G7", "Réunion des chefs d'Etats")
            aDao.insert(article)
            article = Article("Inseparables", "Comédie Française")
            aDao.insert(article)
            return null
        }
    }
    companion object {

        // marking the instance as volatile to ensure atomic access to the variable
        @Volatile
        private var INSTANCE: AppDatabase? = null

        internal fun getDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java, "article_database"
                        )
                            .fallbackToDestructiveMigration()
                            .addCallback(dbCallback)
                            .build()
                    }
                }
            }
            return INSTANCE
        }

        private val dbCallback = object : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                PopulateDbAsync(INSTANCE!!).execute()
            }
        }
    }
}