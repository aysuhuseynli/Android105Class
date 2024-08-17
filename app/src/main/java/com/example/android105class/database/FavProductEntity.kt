package com.example.android105class.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavProduct")
data class FavProductEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    val title:String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    var favStatus: Boolean = true
)
