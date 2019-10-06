package com.mbds.calculatrice

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Historique::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun getInstance(context: Context): AppDatabase =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "calculatriceDB").build()
    }

    abstract fun getHistoriqueDao(): HistoriqueDao
}

object AppDBProvider {

    private var appDB: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        if (appDB == null) {
            appDB = AppDatabase.getInstance(context)
        }
        return appDB!!
    }

}