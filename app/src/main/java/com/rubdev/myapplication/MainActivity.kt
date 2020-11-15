package com.rubdev.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.rubdev.myapplication.databinding.ActivityMainBinding
import com.rubdev.myapplication.model.GitRepoSearchResult
import com.rubdev.myapplication.ui.GitRepoAdapter
import com.rubdev.myapplication.ui.GitSearchRepoViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: GitSearchRepoViewModel
    private val adapter = GitRepoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, Injection.provideViewModelFactory())
            .get(GitSearchRepoViewModel::class.java)

        val dividers = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.listRepositories.addItemDecoration(dividers)
        setupScrollListener()

        initAdapter()

        val query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
        if (viewModel.gitRepoResult.value == null) {
            viewModel.searchRepo(query)
        }




//        val call = RetrofitInstance.servideApi().getAllJavaRepositories()
//        call.enqueue(retrofit2.Callback<List<GithubRepo>>)
    }

    private fun setupScrollListener() {

    }


    private fun initAdapter() {
        binding.listRepositories.adapter
        viewModel.gitRepoResult.observe(this) { result ->
            when (result) {
                is GitRepoSearchResult.Success -> {
                    showEmptyList(result.data.isEmpty())
                    adapter.submitList(result.data)
                }
                is GitRepoSearchResult.Error -> {
                    Toast.makeText(
                        this,
                        "Error {$result.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


    private fun showEmptyList(show: Boolean) {
        if (show) {
            binding.emptyList.visibility = View.VISIBLE
            binding.listRepositories.visibility = View.GONE
        } else {
            binding.emptyList.visibility = View.GONE
            binding.listRepositories.visibility = View.VISIBLE
        }
    }

    companion object {
        private const val LAST_SEARCH_QUERY: String = "last_search_query"
        private const val DEFAULT_QUERY = "Java"
    }
}