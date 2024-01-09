
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.buharija.ChapterList
import com.example.buharija.HadithRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Homepage(navController: NavController, chapters: List<String>) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ChapterList(chapters = chapters, navController = navController)
        }
}

@Preview(showBackground = true)
@Composable
fun HomepagePreview() {
    val hadithRepository = HadithRepository(LocalContext.current)
    val chapterNames: List<String> = hadithRepository.getChapterNames()
}