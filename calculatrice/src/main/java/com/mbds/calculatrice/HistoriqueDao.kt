package com.mbds.calculatrice

import androidx.room.*

@Dao
interface HistoriqueDao {
    @Query("SELECT * FROM historique")
    fun getAll(): List<Historique>

    @Query("DELETE FROM historique")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(hist: Historique)
}