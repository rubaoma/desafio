package com.rubdev.myapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rubdev.myapplication.R
import com.rubdev.myapplication.model.GithubRepo

class RepoGitViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val userNameRepo: TextView = view.findViewById(R.id.username_repo)
    private val nameSurname: TextView = view.findViewById(R.id.name_surname)
    private val description: TextView = view.findViewById(R.id.description_repo)
    private val numberStars: TextView = view.findViewById(R.id.number_stars)
    private val numberForks: TextView = view.findViewById(R.id.number_forks)
    private val avatarImage: ImageView = view.findViewById(R.id.avatar_image)

    private var githubRepo: GithubRepo? = null

    init { //TODO implementar
//        view.setOnClickListener {
//            val intent = Intent(this, PullRequestListActivity::class.java)
//        } startActivity(intent)
    }

    fun bind(gitRepo: GithubRepo?) {
        if (gitRepo == null) {
            val resources = itemView.resources
            userNameRepo.text = resources.getString(R.string.wait)
            nameSurname.text = resources.getString(R.string.wait)
            description.visibility = View.GONE
            numberStars.text = resources.getString(R.string.number_stars)
            numberForks.text = resources.getString(R.string.number_fork)
        } else {
            showGitRepoData(gitRepo)
        }

    }

    private fun showGitRepoData(gitRepo: GithubRepo) {

        this.githubRepo = gitRepo
        userNameRepo.text = gitRepo.repoName
        nameSurname.text = gitRepo.nameSurname

        var descriptionVisibility = View.GONE
        description.text = gitRepo.description
        descriptionVisibility = View.VISIBLE
        description.visibility = descriptionVisibility
        numberStars.text = gitRepo.stars.toString()
        numberForks.text = gitRepo.forks.toString()
    }

    companion object {
        fun create(parent: ViewGroup): RepoGitViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.repository_layout, parent, false)
            return RepoGitViewHolder(view)
        }
    }

}