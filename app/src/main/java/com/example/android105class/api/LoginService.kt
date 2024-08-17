package com.example.android105class.api

import com.example.android105class.dataClass.UserInfo
import com.example.android105class.dataClass.UserRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginService {
    @POST("auth/login")
    suspend fun login(@Body userRequest: UserRequest):Response<UserInfo>

    @POST("auth/refresh")
    suspend fun getRefreshToken(@Body refreshToken:String):Response<String>
}