package com.example.buharija

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ChapterList(chapters: List<String>, navController: NavController) {
    LazyColumn {
        itemsIndexed(chapters) { index,chapter ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        Log.d("ChapterList", "Clicked on chapter: $chapter at index: $index")

                        // Navigate to HadithList screen when a chapter is clicked
                        navController.navigate("hadith_list/$index")

                        Log.d("ChapterList", "pass")

                    }
                    .padding(16.dp)
            ) {
                Text(
                    text = chapter,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChapterListPreview(){
    val hadithRepository = HadithRepository(LocalContext.current)
    // Load hadiths using the repository
    val chapters: List<String> = hadithRepository.getChapterNames()

    HadithList(hadithRepository.getHadithsForChapter(1))
}
