package kalbe.corp.navigationsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kalbe.corp.navigationsample.ui.theme.NavigationSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "firstScreen"){
        composable("firstScreen"){
            FirstScreen {
                name -> navController.navigate("secondScreen/$name")
            }
        }
        composable(route = "secondScreen/{name}"){
            val name = it.arguments?.getString("name") ?: "Unknown"

            SecondScreen(name, {}) {
                navController.navigate("firstScreen")
                navController.navigate("thirdScreen")
            }
        }
        composable(route="thirdScreen"){
            ThirdScreen {
                navController.navigate("firstScreen")
            }
        }
    }
}