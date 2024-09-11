package com.dzcoding.newsapp.Activity.Detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dzcoding.newsapp.Activity.Detail.Screen.NewsDetailScreen
import com.dzcoding.newsapp.Activity.Detail.ui.theme.NewsAppTheme
import com.dzcoding.newsapp.Room.ViewModel.NewsViewModel

class NewsDetailActivity : ComponentActivity() {
    val viewModel:NewsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NewsDetailScreen(this, viewModel = viewModel)
                }
            }
        }
    }
}

