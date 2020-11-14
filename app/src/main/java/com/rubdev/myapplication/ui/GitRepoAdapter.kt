package com.rubdev.myapplication.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rubdev.myapplication.model.GithubRepo

class GitRepoAdapter : ListAdapter<GithubRepo, RepoGitViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoGitViewHolder {
        return RepoGitViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RepoGitViewHolder, position: Int) {
        val gitRepoItem = getItem(position)
        if (gitRepoItem != null) {
            (holder as RepoGitViewHolder).bind(gitRepoItem)
        }

    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<GithubRepo>() {
            override fun areItemsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean =
                oldItem.nameSurname == newItem.nameSurname

            override fun areContentsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean =
                oldItem == newItem
        }
    }
}