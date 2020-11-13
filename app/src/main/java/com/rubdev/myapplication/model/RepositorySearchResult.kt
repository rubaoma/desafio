package com.rubdev.myapplication.model

import java.lang.Exception

sealed class RepositorySearchResult {
    data class Success(val data: List<GithubRepository>): RepositorySearchResult()
    data class Error(val error: Exception): RepositorySearchResult()
}