package com.rubdev.myapplication.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class pullRequestOfRepository(
    @SerializedName("")
    val title: String,
    @SerializedName("")
    val date: Date,
    @SerializedName("")
    val body: String

)