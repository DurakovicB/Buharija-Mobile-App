import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.buharija.Hadith

@Composable
fun SearchResults(hadiths: List<Hadith>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Omitting TopAppBar for simplicity

        Spacer(modifier = Modifier.height(8.dp)) // Adjust the height as needed

        LazyColumn {
            itemsIndexed(hadiths) { index, hadith ->
                Text(
                    text = hadith.text,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}