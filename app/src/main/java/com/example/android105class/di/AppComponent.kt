package com.example.android105class.di

import com.example.android105class.FavoriteFragment
import com.example.android105class.HomeFragment
import com.example.android105class.fragments.LoginFragment
import com.example.android105class.fragments.MainActivity
import com.example.android105class.fragments.ProductsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,NetworkModule::class,DatabaseModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: LoginFragment)
    fun inject(fragment: ProductsFragment)
    fun inject(fragment: FavoriteFragment)
    fun inject(fragment: HomeFragment)
}