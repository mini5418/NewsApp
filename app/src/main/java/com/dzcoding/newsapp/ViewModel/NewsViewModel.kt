package com.dzcoding.newsapp.ViewModel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.dzcoding.newsapp.Model.Article
import com.dzcoding.newsapp.Model.Source


class NewsViewModel:ViewModel() {
   private val _NewsData = mutableStateOf<List<Article>>(emptyList())
    val NewsData:State<List<Article>> = _NewsData


    val API_KEY = "8515c63b6e0df2152f2a796236ef331f"

    fun fetchNewsData(newsQuery:String,context:Context){
        val newsList = mutableListOf<Article>()
        val categoryNews:String = if (newsQuery.isEmpty()){
            "Sport"
        }else{
            newsQuery
        }
        val url = "https://gnews.io/api/v4/search?q=$categoryNews&apikey=$API_KEY"
        val JsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,url,null,{
                                        val jsonArray = it.getJSONArray("articles")

                for (i in 0 until jsonArray.length()){
                    val articleJsonObject = jsonArray.getJSONObject(i)
                    val sourceJsonObject = articleJsonObject.getJSONObject("source")
                    val source = Source(name = sourceJsonObject.getString("name"))
                    val article = Article(
                         description = articleJsonObject.getString("description"),
                        image = articleJsonObject.getString("image"),
                        publishedAt = articleJsonObject.getString("publishedAt"),
                        source = source,
                        title = articleJsonObject.getString("title"),
                        url = articleJsonObject.getString("url")
                    )
                    newsList.add(article)
                    _NewsData.value = newsList
                }

            },{

            }
        )
        Volley.newRequestQueue(context).add(JsonObjectRequest)

    }
}