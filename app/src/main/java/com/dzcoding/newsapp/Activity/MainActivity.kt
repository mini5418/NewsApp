package com.dzcoding.newsapp.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dzcoding.newsapp.Activity.Pages.FavouritePage
import com.dzcoding.newsapp.Activity.Pages.HomePage
import com.dzcoding.newsapp.Model.BottomNavigationItem
import com.dzcoding.newsapp.ViewModel.NewsViewModel
import com.dzcoding.newsapp.ui.theme.NewsAppTheme

class MainActivity : ComponentActivity() {
    val viewModel:NewsViewModel by viewModels()
    val newsViewModel:com.dzcoding.newsapp.Room.ViewModel.NewsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ShowBottomNavigation()

                }
            }
        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    @Preview(showBackground = true)
    fun ShowBottomNavigation(){
        val item = listOf(
            BottomNavigationItem(
                title = "Home",
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home
            ),
            BottomNavigationItem(
                title = "Favourite",
                selectedIcon = Icons.Filled.Favorite,
                unselectedIcon = Icons.Outlined.FavoriteBorder
            )
        )
        var indexItem by rememberSaveable{
            mutableIntStateOf(0)
        }
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                NavigationBar {
                    item.forEachIndexed{index,item->
                        NavigationBarItem(
                            selected = indexItem==index,
                            label = {
                                Text(item.title)
                            },
                            onClick = {
                                indexItem=index
                                navController.navigate(item.title)

                            },
                            icon = {
                                BadgedBox(badge = {}){
                                    Icon(
                                        imageVector = if(index==indexItem){
                                            item.selectedIcon
                                        }else item.unselectedIcon,
                                        contentDescription = item.title
                                    )
                                }

                            }
                        )

                    }
                }
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = "Home"
            ){
                composable("Home"){
                    HomePage(viewModel = viewModel)
                }
                composable("Favourite"){
                    FavouritePage(newsViewModel)
                }
            }

        }
    }
}

