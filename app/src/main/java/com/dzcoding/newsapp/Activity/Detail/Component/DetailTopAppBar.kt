package com.dzcoding.newsapp.Activity.Detail.Component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dzcoding.newsapp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    onFavouriteClick:()->Unit,
    onShareClick:()-> Unit,
    onBrowserClick:()->Unit,
    onBackClick:()->Unit,
    FavouriteIcon:ImageVector
){
    TopAppBar(
        title = {},
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            navigationIconContentColor = Color.Black,
            actionIconContentColor = Color.Black
        ),
        navigationIcon = {
            IconButton(onClick = onBackClick){
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.padding(10.dp))
            }

        },
        actions = {
            IconButton(onClick = onFavouriteClick){
                Icon(imageVector =FavouriteIcon,
                    contentDescription = null,
                    modifier = Modifier.padding(10.dp))
            }

            IconButton(onClick = onShareClick){
                Icon(imageVector = Icons.Default.Share,
                    contentDescription = null,
                    modifier = Modifier.padding(10.dp))
            }
            IconButton(onClick = onBrowserClick){
                Icon(painter = painterResource(R.drawable.outline_browser_updated_24),
                    contentDescription = null,
                    modifier = Modifier.padding(10.dp))
            }


        }
    )
}
@Composable
@Preview(showBackground = true)
fun ShowTopAppBar(){
    DetailTopBar(
        onFavouriteClick = {},
        onShareClick = {},
        onBrowserClick = {},
        onBackClick = {},
        FavouriteIcon = Icons.Default.Favorite
    )
}