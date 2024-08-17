package com.example.android105class.di

import android.app.Application

class MyApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}