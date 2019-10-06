package com.mbds.calculatrice

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Historique(
    @PrimaryKey(autoGenerate = true)
    val uid: Long,
    @ColumnInfo(name = "historique") val historique: String?
)