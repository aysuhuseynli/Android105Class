package com.example.android105class.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavProductEntity::class], version = 1)
abstract class RoomDB : RoomDatabase() {

    abstract fun FavProductDao():FavProductDao
}