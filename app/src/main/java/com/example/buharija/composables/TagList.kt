import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.buharija.HadithRepository
import com.example.buharija.Tag

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagList(navController: NavController, tags: List<Tag>, hadithRepository: HadithRepository) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Bookmarks",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(8.dp)
                )
            },
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp)) // Adjust the height as needed

        LazyColumn {
            itemsIndexed(tags) { index, tag ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .shadow(2.dp, shape = MaterialTheme.shapes.medium),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(16.dp)
                    ) {
                        // Tag name and Hadith count
                        Text(
                            text = "${tag.name} - ${tag.hadithIds.size} Hadiths",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp)
                                .clickable {
                                    // Handle tag click by navigating to the bookmark route with hadith IDs
                                    val hadithIds = tag.hadithIds
                                    navController.navigate("bookmarks/${tag.name}/${hadithIds.joinToString(",")}")
                                }
                        )
                    }
                }
            }
        }

        // Button at the mid bottom of the screen
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    hadithRepository.addEmptyTag()
                }
            ) {
                Text(text = "Add Tag")
            }
        }
    }
}
