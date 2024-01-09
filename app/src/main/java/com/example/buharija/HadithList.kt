package com.example.buharija

import android.annotation.SuppressLint
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HadithList(chapterName: String, hadiths: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = {
                val fontSize: TextUnit
                if((chapterName.length>40)and(chapterName.length<65)) fontSize=17.sp
                else if(chapterName.length<40) fontSize=24.sp
                else fontSize=15.sp
                Text(
                    text = chapterName,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = fontSize
                )
            },
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp)) // Adjust the height as needed

        LazyColumn {
            itemsIndexed(hadiths) { index, hadith ->
                Text(
                    text = hadith,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}






