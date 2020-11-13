package com.rubdev.myapplication.data

import com.rubdev.myapplication.api.GitHubApiService
import com.rubdev.myapplication.api.IN_QUALIFIER
import com.rubdev.myapplication.model.GithubRepository
import com.rubdev.myapplication.model.RepoResponse
import com.rubdev.myapplication.model.RepositorySearchResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import retrofit2.HttpException
import java.io.IOException
import

@ExperimentalCoroutinesApi
class GitRepository(private val apiService: GitHubApiService) {

    private val inMemoryCache = mutableListOf<GithubRepository>()
    private val searhResults = ConflatedBroadcastChannel<RepoResponse>()
    private var lastRequestedPage = GITHUB_STARTING_PAGE_INDEX
    private var isRequestInProgress = false

    suspend fun getSearchResultStream(query: String): Flow<RepoResponse> {
        lastRequestedPage = 1
        inMemoryCache.clear()
        requestAndSaveData(query)

        return searhResults.asFlow()
    }

    suspend fun requestMore(query: String) {
        if (isRequestInProgress)
            return
        val sucessful = requestAndSaveData(query)
        if (sucessful) lastRequestedPage++
    }

    private suspend fun requestAndSaveData(query: String): Boolean {
        isRequestInProgress = true
        var successful = false

        val apiQuery = query + IN_QUALIFIER
        try {
            val response = apiService.getAllJavaRepositories(apiQuery, lastRequestedPage, NETWORK_PAGE_SIZE)
            val repos = response.items ?: emptyList()
            inMemoryCache.addAll(repos)
            val reposByName = reposByName(query)
            searhResults.offer(RepositorySearchResult.Success(reposByName))
            successful = true
        }catch (exception: IOException){
            searhResults.offer(RepositorySearchResult.Error(exception))
        } catch (exception: HttpException){
            searhResults.offer(RepositorySearchResult.Error(exception))
        }
        isRequestInProgress = false
        return successful
    }

    private fun reposByName(query: String): List<GithubRepository> {
        return inMemoryCache.filter {
            it.authorName.contains(query, true) || (it.description != null && it.description.contains(query,true))
        }.sortedWith(compareByDescending<GithubRepository> { it.stars }.thenBy { it.authorName })
    }
    companion object{
        private const val NETWORK_PAGE_SIZE = 50
    }
}