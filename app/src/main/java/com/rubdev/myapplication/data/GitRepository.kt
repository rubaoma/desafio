package com.rubdev.myapplication.data


import com.rubdev.myapplication.api.IN_QUALIFIER
import com.rubdev.myapplication.api.ServicesApi
import com.rubdev.myapplication.model.GitRepoSearchResult
import com.rubdev.myapplication.model.GithubRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import retrofit2.HttpException
import java.io.IOException

private const val GITHUB_STARTING_PAGE_INDEX = 1

@ExperimentalCoroutinesApi
class GitRepository(private val apiService: ServicesApi) {

    private val inMemoryCache = mutableListOf<GithubRepo>()
    private val searhResults = ConflatedBroadcastChannel<GitRepoSearchResult>()
    private var lastRequestedPage = GITHUB_STARTING_PAGE_INDEX
    private var isRequestInProgress = false

    @FlowPreview
    suspend fun getSearchResultStream(query: String): Flow<GitRepoSearchResult> {
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
            val response =
                apiService.getAllJavaRepositories(apiQuery, lastRequestedPage, NETWORK_PAGE_SIZE)
            val repos = response.items
            inMemoryCache.addAll(repos)
            val reposByName = reposByName(query)
            searhResults.offer(GitRepoSearchResult.Success(reposByName))
            successful = true
        } catch (exception: IOException) {
            searhResults.offer(GitRepoSearchResult.Error(exception))
        } catch (exception: HttpException) {
            searhResults.offer(GitRepoSearchResult.Error(exception))
        }
        isRequestInProgress = false
        return successful
    }

    private fun reposByName(query: String): List<GithubRepo> {
        return inMemoryCache.filter {
            it.repoName.contains(query, true) || (it.description.contains(
                query,
                true
            ))
        }.sortedWith(compareByDescending<GithubRepo> { it.stars }.thenBy { it.repoName })
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}