package com.dzcoding.newsapp.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dzcoding.newsapp.Room.Entity.NewsFavourite

@Dao
interface NewsDao {
    @Query("SELECT * FROM  news")
    fun getAllNews():LiveData<List<NewsFavourite>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNews(newsFavourite: NewsFavourite)
    @Query("DELETE FROM news WHERE url = :url")
    suspend fun deleteNews(url:String)

}