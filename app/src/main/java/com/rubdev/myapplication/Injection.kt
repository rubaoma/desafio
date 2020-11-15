package com.rubdev.myapplication

import androidx.lifecycle.ViewModelProvider
import com.rubdev.myapplication.api.ServicesApi
import com.rubdev.myapplication.data.GitRepository
import com.rubdev.myapplication.ui.ViewModelFactory

object Injection {

    private fun provideGitHubRepository(): GitRepository {
        return GitRepository(ServicesApi.create())
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(provideGitHubRepository())
    }
}