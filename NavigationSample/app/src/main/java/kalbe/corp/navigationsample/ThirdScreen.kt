package kalbe.corp.navigationsample

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ThirdScreen(navigateToFirstScreen: () -> Unit){
    Button(onClick = {navigateToFirstScreen()}) {
        Text("This is the Third Screen")
    }
}

@Preview
@Composable
fun ThirdScreenPreview(navigateToFirstScreen: () -> Unit){
    Button(onClick = {navigateToFirstScreen()}) {
        Text("This is the Third Screen")
    }
}