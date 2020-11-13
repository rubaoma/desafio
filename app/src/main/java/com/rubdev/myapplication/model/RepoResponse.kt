package com.rubdev.myapplication.model

import com.google.gson.annotations.SerializedName

data class RepoResponse (
    @SerializedName("total_count")
    val total: Int = 0,
    @SerializedName("items")
    val items: List<GithubRepository> = emptyList(),
    val nextPage: Int? = null
)