package com.rubdev.myapplication.ui

import androidx.lifecycle.*
import com.rubdev.myapplication.data.GitRepository
import com.rubdev.myapplication.model.GitRepoSearchResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class GitSearchPullViewModel(
    private val repository: GitRepository
): ViewModel() {

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }

    private val queryLiveData = MutableLiveData<String>()
    val gitRepoResult: LiveData<GitRepoSearchResult> = queryLiveData.switchMap { querySring ->
        liveData {
            val repos = repository.getSearchResultStream(querySring).asLiveData(Dispatchers.Main)
            emitSource(repos)
        }
    }

    fun searchRepo(queryString: String){
        queryLiveData.postValue(queryString)
    }

    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount){
            val immutableQuery = queryLiveData.value
            if (immutableQuery !=null){
                viewModelScope.launch {
                    repository.requestMore(immutableQuery)
                }
            }
        }
    }
}