package com.rubdev.myapplication.api

import com.rubdev.myapplication.model.GithubRepo
import retrofit2.Call
import retrofit2.http.GET

interface ServicesApi {
    @GET("search/repositories?q=language:Java&sort=stars&page=1")
    fun getAllJavaRepositories(): Call<List<GithubRepo>>

}