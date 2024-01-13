
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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SearchMenu( navController: NavController) {
    var searchTerm by remember { mutableStateOf("") }
    var exactPhrase by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Pretraživanje",
                    fontWeight = FontWeight.Bold
                )
            }
        )
        // Text input field for search query
        TextField(
            value = searchTerm,
            onValueChange = { searchTerm = it },
            label = { Text("Unesite izraz!") },
            modifier = Modifier.fillMaxWidth()
        )

        if (searchTerm.isBlank()) {
            Text(
                text = "Polje ne može biti prazno!",
                color = Color.Red,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

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
            Text("Traženje čitavog izraza")
        }
        // Search button
        Button(

            onClick = {
                //hideKeyboard()

                if (searchTerm.isBlank()) {
                } else if (exactPhrase) {
                    navController.navigate("exactsearch/$searchTerm")
                } else {
                    navController.navigate("keywordsearch/$searchTerm")
                }

            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Traži")
        }
    }
}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun hideKeyboard() {
    val keyboardController = LocalSoftwareKeyboardController.current
    keyboardController?.hide()
}