package com.dzcoding.newsapp.Room.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.dzcoding.newsapp.Room.Entity.NewsFavourite
import com.dzcoding.newsapp.Room.Repositry.NewsRepositry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(application: Application):AndroidViewModel(application) {
    private val NewsRepositry:NewsRepositry = NewsRepositry(application)
    fun getAllNews():LiveData<List<NewsFavourite>> = NewsRepositry.getAllNews()
    fun deleteNews(url:String) = viewModelScope.launch(Dispatchers.IO) {
        NewsRepositry.deleteNews(url)
    }
    fun insertNews(newsFavourite: NewsFavourite) = viewModelScope.launch(Dispatchers.IO) {
        NewsRepositry.insertNews(newsFavourite)
    }
}