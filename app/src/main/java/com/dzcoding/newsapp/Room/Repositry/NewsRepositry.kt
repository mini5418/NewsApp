package com.dzcoding.newsapp.Room.Repositry

import android.app.Application
import androidx.lifecycle.LiveData
import com.dzcoding.newsapp.Room.Dao.NewsDao
import com.dzcoding.newsapp.Room.Database.NewsDatabase
import com.dzcoding.newsapp.Room.Entity.NewsFavourite

class NewsRepositry(application: Application) {
    private val NewsDb:NewsDatabase  = application.let {
        NewsDatabase.getDatabase(application)!!
    }
    val dao:NewsDao = NewsDb.NewsDao
    fun getAllNews():LiveData<List<NewsFavourite>>{
        return dao.getAllNews()
    }
    suspend fun deleteNews(url:String){
        dao.deleteNews(url)
    }
    suspend fun insertNews(NewsFavourite:NewsFavourite){
        dao.insertNews(NewsFavourite)
    }
}