
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun DailyHadith(hadith: Hadith) {
    Log.d("DailyHadith", "Composable recomposed")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top bar with the title "Daily Hadith"
        TopAppBar(
            title = {
                Text(
                    text = "Daily Hadith",
                    fontWeight = FontWeight.Bold
                )
            },
                modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        // Spacer to add some space between the top bar and the hadith text
        Spacer(modifier = Modifier.height(16.dp))

        // Text displaying the daily hadith
        Text(
            text = hadith.text,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        )
    }
}