package com.rubdev.myapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rubdev.myapplication.R
import com.rubdev.myapplication.model.GithubRepo
import com.rubdev.myapplication.model.PullRequestOfRepo

class PullGitViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val userNameRepo: TextView = view.findViewById(R.id.user_name_pull)
    private val nameSurname: TextView = view.findViewById(R.id.undername_pull)
    private val description: TextView = view.findViewById(R.id.description_pull_request)
    private val date: TextView = view.findViewById(R.id.date_pull)


    private var githubPull: PullRequestOfRepo? = null

    init {
//        view.setOnClickListener {
//            pullRequestOfRepo?.url?.let { url ->
//                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//                view.context.startActivity(intent)
    }

    fun bind(githubPull: GithubRepo?) {
        if (githubPull == null) {
            val resources = itemView.resources
            userNameRepo.text = resources.getString(R.string.wait)
            nameSurname.text = resources.getString(R.string.wait)
            description.visibility = View.GONE

        } else {
//            PullRequestOfRepo(PullRequestOfRepo)
        }

    }

    private fun showGitRepoData(gitPull: PullRequestOfRepo) {

//        this.githubPull = gitPull
//        userNameRepo.text = gitPull.
//        nameSurname.text = gitPull
//
//        var descriptionVisibility = View.GONE
//        description.text = gitPull.description
//        descriptionVisibility = View.VISIBLE
//        description.visibility = descriptionVisibility
//        n
    }

    companion object {
        fun create(parent: ViewGroup): PullGitViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.repository_layout, parent, false)
            return PullGitViewHolder(view)
        }
    }

}