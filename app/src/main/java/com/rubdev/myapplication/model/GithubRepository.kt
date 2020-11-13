package com.rubdev.myapplication.model

import com.google.gson.annotations.SerializedName

data class GithubRepository(
    @SerializedName("")
    val repositoryName: String,
    @SerializedName("")
    val description: String,
    @SerializedName("")
    val authorName: String,
    @SerializedName("")
    val stars: String,
    @SerializedName("")
    val forks: String

)