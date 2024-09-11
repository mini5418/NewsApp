package com.dzcoding.newsapp.Activity.Pages

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dzcoding.newsapp.Activity.Detail.NewsDetailActivity
import com.dzcoding.newsapp.Model.Article
import com.dzcoding.newsapp.Room.Entity.NewsFavourite
import com.dzcoding.newsapp.Room.ViewModel.NewsViewModel


@Composable
fun FavouritePage(viewModel: NewsViewModel){
val newsList by viewModel.getAllNews().observeAsState(emptyList())
    ShowNewsFavouriteList(newsList, viewModel = viewModel)

}

@Composable
fun ShowNewsFavouriteList(article:List<NewsFavourite>, viewModel: com.dzcoding.newsapp.Room.ViewModel.NewsViewModel){
    val context = LocalContext.current
    Column {

        LazyColumn {
            items(article){
                Row (
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable{
                        val intent = Intent(context, NewsDetailActivity::class.java).apply {
                            putExtra("url",it.url)
                            putExtra("title",it.title)
                            putExtra("description",it.description)
                            putExtra("image",it.image)
                            putExtra("source_name",it.sourceName)
                            putExtra("published",it.publish_Date)
                        }
                        context.startActivity(intent)
                    }
                ){
                    AsyncImage(
                        model = it.image,
                        contentDescription = it.title,
                        modifier = Modifier.padding(start = 10.dp).size(150.dp)
                    )
                    Column(verticalArrangement = Arrangement.SpaceBetween) {
                        Text(it.title,
                            style = TextStyle(fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                            ),
                            modifier = Modifier.padding(start = 10.dp)
                        )
                        Text(it.sourceName,
                            style = TextStyle(fontSize = 10.sp),
                            modifier = Modifier.padding(start = 10.dp)
                        )
                        Text(it.publish_Date,
                            style = TextStyle(fontSize = 10.sp),
                            modifier = Modifier.padding(start = 10.dp)
                        )

                    }
                }

            }
        }

    }
}