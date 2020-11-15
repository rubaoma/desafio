package com.rubdev.myapplication.model

import java.lang.Exception

sealed class GitRepoSearchResult {
    data class Success(val data: List<GithubRepo>): GitRepoSearchResult()
    data class Error(val error: Exception): GitRepoSearchResult()
}