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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.buharija.Hadith

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResults(hadiths: List<Hadith>, searchTerm: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Top bar with the title "Search Results: search term"
        TopAppBar(
            title = {
                Text(
                    text = "Pretraživanje: '$searchTerm'",
                    fontWeight = FontWeight.Bold
                )
            },
                    modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        // Spacer to add some space between the top bar and the search results
        Spacer(modifier = Modifier.height(8.dp))

        // Text displaying the count of results
        Text(
            text = "Broj rezultata: ${hadiths.size}",
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        // Spacer to add some space between the count and the search results
        Spacer(modifier = Modifier.height(8.dp))

        // LazyColumn displaying the search results
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
