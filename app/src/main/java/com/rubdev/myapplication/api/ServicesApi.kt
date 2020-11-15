package com.rubdev.myapplication.api

import com.rubdev.myapplication.model.GithubRepo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val IN_QUALIFIER = "in:name,description"

interface ServicesApi {
    @GET("search/repositories?q=language:Java&sort=stars")
    suspend fun getAllJavaRepositories(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") intemsPerPage: Int
    ): GitRepoSearchResponse

    companion object {
        private const val BASE_URL = "https://api.github.com/"
        fun create(): ServicesApi {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ServicesApi::class.java)
        }
    }


}