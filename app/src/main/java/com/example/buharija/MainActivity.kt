package com.example.buharija

import Homepage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val hadithRepository = HadithRepository(LocalContext.current)
            val chapterNames: List<String> = hadithRepository.getChapterNames()
            Scaffold(
                topBar = {
                    // TopAppBar remains the same
                },
                bottomBar = {
                    CustomBottomBar()
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
                        Homepage(navController = navController, chapters = chapterNames)
                    }
                    composable("hadith_list/{chapterId}") { backStackEntry ->
                        // Extract the chapterId from the route
                        val chapterId = backStackEntry.arguments?.getString("chapterId")
                        val hadiths = hadithRepository.getHadithsForChapter(chapterId!!.toInt())
                        val chapterName= hadithRepository.getChapterName(chapterId!!.toInt())
                        HadithList(hadiths = hadiths, chapterName = chapterName)
                    }
                }

            }
        }
    }
}
@Composable
fun CustomBottomBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomBarItem(icon = Icons.Default.Add, label = "Read")
        BottomBarItem(icon = Icons.Default.Search, label = "Search")
        BottomBarItem(icon = Icons.Default.AccountCircle, label = "Bookmarks")
        BottomBarItem(icon = Icons.Default.Call, label = "Daily Hadith")
    }
}

@Composable
fun BottomBarItem(icon: ImageVector, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
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

@Preview(showBackground = true)
@Composable
fun BuharijaPreview() {

}

