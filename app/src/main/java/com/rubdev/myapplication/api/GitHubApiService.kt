package com.rubdev.myapplication.api

import com.rubdev.myapplication.model.RepoResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val IN_QUALIFIER = "in:authorName,description"

interface GitHubApiService {

    @GET("search/repositories?q=language:Java&sort=stars&page=1")
    suspend fun getAllJavaRepositories(
        @Query("api_key") key: String,
        lastRequestedPage: Any,
        NETWORK_PAGE_SIZE: Int

    ): RepoResponse

    //TODO implement pullRequest
//    @GET("")
//    fun getPullRequests()
}