package com.dzcoding.newsapp.Room.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "News")
data class NewsFavourite(
@PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val title:String,
    val description:String,
    val publish_Date:String,
    val sourceName:String,
    val image:String,
    val url:String
)
