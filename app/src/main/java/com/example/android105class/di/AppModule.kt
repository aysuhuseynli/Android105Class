package com.example.android105class.di

import android.content.Context
import dagger.Module
import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {
    @Provides
    fun provideContext():Context{
        return context.applicationContext
    }
}