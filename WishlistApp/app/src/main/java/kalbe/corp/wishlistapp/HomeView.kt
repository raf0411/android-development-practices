package kalbe.corp.wishlistapp

import android.R.attr.contentDescription
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.FloatingActionButton
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.navigation.NavController

@Composable
fun HomeView(
    navController: NavController,
    viewModel: WishViewModel,
) {
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp), topBar = {
        TopBarView(
            title = "Wishlist", onBackNavClick = {
                Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show()
            })
    }, floatingActionButton = {
        FloatingActionButton(
            modifier = Modifier.padding(20.dp), backgroundColor = Color.Black, onClick = {
                navController.navigate(Screen.AddScreen.route)
            }) {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = Color.White,
            )
        }
    }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(
                DummyDataWish.wishlist
            ) { wish ->
                WishItem(wish = wish) {}
            }
        }
    }
}