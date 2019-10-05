package fr.unice.mbds.gestionarticles

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Article (
    @field:ColumnInfo(name = "title") var title: String?,
    @field:ColumnInfo(name = "description") var description: String?){

    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0

}