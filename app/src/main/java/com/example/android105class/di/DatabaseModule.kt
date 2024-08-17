package com.example.android105class.di

import android.content.Context
import androidx.room.Room
import com.example.android105class.database.FavProductDao
import com.example.android105class.database.RoomDB
import dagger.Module
import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    fun provideRoomDatabase(context: Context):RoomDB{
        return Room.databaseBuilder(
            context.applicationContext,
            RoomDB::class.java,
            "FavProductDatabase"
        ).build()
    }

    @Provides
    fun provideFavProductDao(roomDB: RoomDB):FavProductDao{
        return roomDB.FavProductDao()
    }
}