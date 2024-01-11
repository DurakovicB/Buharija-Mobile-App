import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SearchMenu( navController: NavController) {
    var searchTerm by remember { mutableStateOf("") }
    var exactPhrase by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Text input field for search query
        TextField(
            value = searchTerm,
            onValueChange = { searchTerm = it },
            label = { Text("Label") }
        )

        // Checkbox for exact phrase search
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = exactPhrase,
                onCheckedChange = { isChecked -> exactPhrase = isChecked },
                modifier = Modifier.padding(end = 8.dp)
            )
            Text("Exact Phrase Search")
        }

        // Search button
        Button(
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Search")
        }
    }
}

