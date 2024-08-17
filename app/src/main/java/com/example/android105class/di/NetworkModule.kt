package com.example.android105class.di

import com.example.android105class.api.LoginService
import com.example.android105class.api.ProductService
import com.example.android105class.api.Token
import dagger.Module
import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    fun provideTokenInterceptor():Interceptor{
        return Interceptor{ chain->
            val originalRequest=chain.request()
            val newRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer ${Token.token}")
                .build()
            chain.proceed(newRequest)
        }
    }

    @Provides
    fun provideOkHttpClient(tokenInterceptor:Interceptor):OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(tokenInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofitInstance(client:OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun provideProductService(retrofit: Retrofit):ProductService{
        return retrofit.create(ProductService::class.java)
    }

    @Provides
    fun provideLoginService(retrofit: Retrofit):LoginService{
        return retrofit.create(LoginService::class.java)
    }
}