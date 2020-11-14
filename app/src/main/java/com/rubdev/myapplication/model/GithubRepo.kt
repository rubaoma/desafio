package com.rubdev.myapplication.model

import com.google.gson.annotations.SerializedName

data class GithubRepo(
    @field:SerializedName("id")
    val id: Long,
    @field:SerializedName("name")
    val repoName: String,
    @field:SerializedName("full_name")
    val nameSurname: String,
    @field:SerializedName("description")
    val description: String,
    @field:SerializedName("starsgazers_count")
    val stars: Int,
    @field:SerializedName("forks_count")
    val forks: Int,
    @field:SerializedName("html_url")
    val url: String,
    @field:SerializedName("avatar_url")
    val avatar: String,
    @field:SerializedName("pull_url")
    val pullUrl: String,


)