
import android.content.ClipData
import android.content.ClipboardManager
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.getSystemService
import androidx.navigation.NavController
import com.example.buharija.Hadith
import com.example.buharija.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyHadith(navController: NavController, hadith: Hadith) {
    val context = LocalContext.current
    val clipboard = context.getSystemService<ClipboardManager>()

    Log.d("Hadis dana", "Composable recomposed")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        // Top bar with the title "Daily Hadith"
        TopAppBar(
            title = {
                Text(
                    text = "Hadis Dana",
                    fontWeight = FontWeight.Bold,
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        // Spacer to add some space between the top bar and the hadith text
        Spacer(modifier = Modifier.height(16.dp))

        // LazyColumn for the main content to enable scrolling
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            item {
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
                        // Hadith ID in smaller font
                        Text(
                            text = "ID Hadisa: ${hadith.id}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp)
                        )

                        // Hadith text
                        Text(
                            text = hadith.text,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.fillMaxWidth()
                        )

                        // Buttons row
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Copy button
                            IconButton(
                                onClick = {

                                    clipboard?.setPrimaryClip(ClipData.newPlainText("Hadith", hadith.text))
                                    Toast.makeText(context, "Hadis kopiran!", Toast.LENGTH_SHORT).show()

                                }
                            ) {

                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_content_copy_24 ),
                                    contentDescription = "Copy",
                                    modifier = Modifier.size(24.dp)
                                )
                            }

                            IconButton(
                                onClick = {
                                    navController.navigate("dailyhadith")
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Refresh,
                                    contentDescription = "Refresh",
                                    modifier = Modifier.size(24.dp)
                                )
                            }

                            // Favorite button
                            IconButton(
                                onClick = {
                                    // Implement favorite functionality
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Share,
                                    contentDescription = "Favorite",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}

