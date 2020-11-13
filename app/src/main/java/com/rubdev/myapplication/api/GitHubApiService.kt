package com.rubdev.myapplication.api

import com.rubdev.myapplication.model.GithubRepository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApiService {

    @GET("search/repositories?q=language:Java&sort=stars&page=1")
    fun getAllJavaRepositories(@Query("api_key") key: String): Call<GithubRepository>

    @GET("")
    fun getPullRequests()
}