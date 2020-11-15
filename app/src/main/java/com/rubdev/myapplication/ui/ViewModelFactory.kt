package com.rubdev.myapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rubdev.myapplication.data.GitRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repository: GitRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GitSearchRepoViewModel::class.java)) {
            return GitSearchRepoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown VielModel class")
    }
}