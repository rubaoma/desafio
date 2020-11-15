package com.rubdev.myapplication.api

import com.google.gson.annotations.SerializedName
import com.rubdev.myapplication.model.GithubRepo

data class GitRepoSearchResponse (
    @SerializedName("total_count")
    val total: Int = 0,
    @SerializedName("itens")
    val items: List<GithubRepo> = emptyList(),
    val nextPage: Int? = null
)