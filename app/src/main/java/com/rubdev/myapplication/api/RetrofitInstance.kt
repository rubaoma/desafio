package com.rubdev.myapplication.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {


    private val BASE_URL: String = "https://api.github.com/"


    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun servideApi(): ServicesApi = retrofit.create(ServicesApi::class.java)



}

