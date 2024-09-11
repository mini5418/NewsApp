package com.dzcoding.newsapp.Activity.Pages

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dzcoding.newsapp.Activity.Detail.NewsDetailActivity
import com.dzcoding.newsapp.Model.Article
import com.dzcoding.newsapp.R
import com.dzcoding.newsapp.ViewModel.NewsViewModel

@Composable

fun HomePage(viewModel: NewsViewModel){
 viewModel.NewsData.value.let {
     ShowNewsList(it, viewModel = viewModel)
 }



}
@Composable
fun ShowSearch(viewModel:NewsViewModel){
    val context  = LocalContext.current
    var searchText by remember {
        mutableStateOf("")
    }
    TextField(
        value = searchText,
        onValueChange = {searchText=it},
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        label = { Text("Search") }
    )
    viewModel.fetchNewsData(searchText,context)

}
@Composable
fun ShowNewsList( article:List<Article>,viewModel: NewsViewModel){
    val context = LocalContext.current
    Column {
        ShowSearch(viewModel = viewModel)
        LazyColumn {
            items(article){
                Row (
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable{
                        val intent = Intent(context,NewsDetailActivity::class.java).apply {
                            putExtra("url",it.url)
                            putExtra("title",it.title)
                            putExtra("description",it.description)
                            putExtra("image",it.image)
                            putExtra("source_name",it.source.name)
                            putExtra("published",it.publishedAt)
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
                        Text(it.source.name,
                            style = TextStyle(fontSize = 10.sp),
                            modifier = Modifier.padding(start = 10.dp)
                        )
                        Text(it.publishedAt,
                            style = TextStyle(fontSize = 10.sp),
                            modifier = Modifier.padding(start = 10.dp)
                        )

                    }
                }

            }
        }

    }
}