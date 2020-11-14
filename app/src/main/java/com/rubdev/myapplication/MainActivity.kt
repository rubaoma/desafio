package com.rubdev.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rubdev.myapplication.api.RetrofitInstance
import com.rubdev.myapplication.model.GithubRepo

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val call = RetrofitInstance.servideApi().getAllJavaRepositories()
        call.enqueue(retrofit2.Callback<List<GithubRepo>>)
    }
}