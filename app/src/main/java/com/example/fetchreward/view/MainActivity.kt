package com.example.fetchreward.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.lifecycle.ViewModelProvider
import com.example.fetchreward.utils.ViewModelFactory
import com.example.fetchreward.viewModel.retrofitApi.MainViewModel


class MainActivity : AppCompatActivity() {

    private val viewModel :MainViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory(application))[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ExpandableListScreen(viewModel)
            }
        }
    }
}