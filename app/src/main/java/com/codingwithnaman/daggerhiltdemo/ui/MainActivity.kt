package com.codingwithnaman.daggerhiltdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.codingwithnaman.daggerhiltdemo.R
import com.codingwithnaman.daggerhiltdemo.model.Post
import com.codingwithnaman.daggerhiltdemo.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeObservers()
        mainViewModel.setStateEvent(MainStateEvent.GetPostEvent)

    }

    private fun subscribeObservers(){
        mainViewModel.dataState.observe(this, Observer {
            when(it) {
                is DataState.Success -> {
                    displayProgressBar(false)
                    appendBlogTitles(it.data)
                }

                is DataState.Loading -> {
                    displayProgressBar(true)
                }

                is DataState.Error -> {
                    displayProgressBar(false)
                    textView.text = it.exception.message
                }
            }
        })
    }

    private fun appendBlogTitles(posts: List<Post>){
        val sb = StringBuilder()
        for(post in posts){
            sb.append(post.title + "\n")
        }
        textView.text = sb.toString()
    }

    private fun displayProgressBar(isDisplayed : Boolean) {
        progress_circular.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }
}