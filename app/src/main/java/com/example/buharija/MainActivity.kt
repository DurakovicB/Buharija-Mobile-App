package com.example.buharija

import DailyHadith
import Homepage
import SearchMenu
import SearchResults
import TagList
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val hadithRepository = HadithRepository(LocalContext.current)
            Scaffold(
                topBar = {
                    // TopAppBar remains the same
                },
                bottomBar = {
                    CustomBottomBar(navController)
                }
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = "home",
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                ) {
                    composable("home") {
                        // Your home screen content
                        // You can include buttons or other UI elements to trigger navigation
                        Homepage(navController = navController, chapters = hadithRepository.getChapters())
                    }
                    composable("hadith_list/{chapterId}") { backStackEntry ->
                        // Extract the chapterId from the route
                        val chapterId = backStackEntry.arguments?.getString("chapterId")!!.toInt()
                        val chapterName=hadithRepository.getChapterById(chapterId)?.name
                        val hadiths= hadithRepository.getHadithsForChapter(chapterId = chapterId)
                        if(chapterName != null) HadithList(hadiths = hadiths, chapterName = chapterName)
                    }
                    composable("searchMenu"){
                        SearchMenu(navController=navController)
                    }
                    composable("exactsearch/{searchTerm}"){backStackEntry->
                        val phrase = backStackEntry.arguments?.getString("searchTerm")!!
                        SearchResults(hadiths = hadithRepository.searchExactPhrase(phrase), phrase)
                    }
                    composable("keywordsearch/{searchTerm}"){backStackEntry->
                        val phrase = backStackEntry.arguments?.getString("searchTerm")!!


                        SearchResults(hadiths = hadithRepository.searchKeywords(phrase), phrase)
                    }
                    composable("dailyhadith"){
                        DailyHadith(navController,hadithRepository.randomHadith()!!)
                    }
                    composable("bookmarks"){
                        val tags= hadithRepository.getTags()
                     TagList(navController, tags,hadithRepository)
                    }
                    composable("bookmarks/{tag_name}/{hadithIds}") { backStackEntry ->
                        val hadithIds = backStackEntry.arguments?.getString("hadithIds")?.split(",").orEmpty().map { it.toInt() }
                        val tagName=backStackEntry.arguments?.getString("tag_name")
                        val bookmarkedHadiths = hadithIds.mapNotNull { hadithId ->
                            hadithRepository.getHadithById(hadithId)
                        }

                        HadithList(chapterName = tagName!!,hadiths = bookmarkedHadiths)
                    }

                }

            }
        }
    }
}
@Composable
fun CustomBottomBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomBarItem(navController=navController,route="home", icon = Icons.Default.Home, label = "Čitaj")
        BottomBarItem(navController=navController,route="searchMenu", icon = Icons.Default.Search, label = "Traži")
        BottomBarItem(navController=navController,route="bookmarks", icon = Icons.Default.FavoriteBorder, label = "Oznake")
        BottomBarItem(navController=navController,route="dailyhadith", icon = Icons.Default.DateRange, label = "Hadis Dana")
    }
}

@Composable
fun BottomBarItem(navController:NavController,route:String,icon: ImageVector, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
        .clickable {
            // Navigate to HadithList screen when a chapter is clicked
            Log.d("mainact","clickable")
            navController.navigate("$route")
        }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = label,
            color = Color.White,
            style = MaterialTheme.typography.labelSmall
        )
    }
}


