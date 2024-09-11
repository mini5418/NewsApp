package com.dzcoding.newsapp.Activity.Detail.Screen

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import coil.compose.AsyncImage
import com.dzcoding.newsapp.Activity.Detail.Component.DetailTopBar
import com.dzcoding.newsapp.Prefrence.NewsPreferences
import com.dzcoding.newsapp.Room.Entity.NewsFavourite
import com.dzcoding.newsapp.Room.ViewModel.NewsViewModel

@Composable
fun NewsDetailScreen(
    activity:Activity,
    viewModel: NewsViewModel
){
    val context = LocalContext.current
    val url = activity.intent.getStringExtra("url")?:""
    val image  = activity.intent.getStringExtra("image")?:""
    val title = activity.intent.getStringExtra("title")?:""
    val description = activity.intent.getStringExtra("description")?:""
    val sourceName = activity.intent.getStringExtra("source_name")?:""
    val publishedName = activity.intent.getStringExtra("published")?:""
    var isNews by remember {
        mutableStateOf(NewsPreferences.getSharedPrefrences(context,title))
    }
    val favouriteIcon = if (isNews){
        Icons.Default.Favorite
    }else{
        Icons.Default.FavoriteBorder
    }
    val newsFavourite = NewsFavourite(
        title = title,
        description = description,
        image = image,
        url = url,
        sourceName =sourceName,
        publish_Date = publishedName
    )
    Column {
        DetailTopBar(
            onBackClick = {
                          activity.finish()
            },
            onBrowserClick = {
                             val browseintent = CustomTabsIntent.Builder().build()
                             browseintent.launchUrl(activity,Uri.parse(url))

            },
            onShareClick = {
                           val intent = Intent(Intent.ACTION_SEND).apply {
                               putExtra(Intent.EXTRA_TEXT,url)
                               type = "text/plain"
                           }
                val shareIntent = Intent.createChooser(intent,null)
                activity.startActivity(shareIntent)

            },
            onFavouriteClick = {
                isNews = !isNews
                NewsPreferences.SaveSahredPrefences(context,title,isNews)
                if (isNews){
                    viewModel.insertNews(newsFavourite = newsFavourite)
                }else{
                    viewModel.deleteNews(url = url)
                }
            },
            FavouriteIcon = favouriteIcon
        )
        NewsContent(
            image = image,
            title = title,
            description = description
        )
    }
}

@Composable
fun NewsContent(
    image:String,
    title:String,
    description:String
){
    Column{
        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(300.dp).padding(bottom = 10.dp)
        )
        Text(title,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,

            ),
            modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
        )
        Text(description,
            style = TextStyle(
                fontSize = 17.sp,


                ),
            modifier = Modifier.padding(start = 10.dp)
        )

    }
}