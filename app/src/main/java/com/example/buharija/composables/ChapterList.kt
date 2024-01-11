package com.example.buharija

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChapterList(chapters: List<Chapter>, navController: NavController) {
    val firstName = chapters[0].name
    // Assuming you are using Android's Log class
    Log.d("YourTag", "First Name: $firstName ")

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Poglavlja",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(8.dp)
                )


            }


        )



        Spacer(modifier = Modifier.height(8.dp)) // Adjust the height as needed

        LazyColumn {
            itemsIndexed(chapters) { index, chapter ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            // Navigate to HadithList screen when a chapter is clicked
                            navController.navigate("hadith_list/$index")
                        }
                        .padding(16.dp)
                ) {
                    Text(
                        text = chapter.name,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun ChapterListPreview(){

}
