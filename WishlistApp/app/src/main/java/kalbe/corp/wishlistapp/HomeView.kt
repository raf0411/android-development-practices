package kalbe.corp.wishlistapp

import android.R.attr.label
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import kalbe.corp.wishlistapp.data.DummyDataWish

@OptIn(ExperimentalMaterialApi::class)
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
                navController.navigate(Screen.AddScreen.route + "/0L")
            }) {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = Color.White,
            )
        }
    }) {
        val wishlist = viewModel.getAllWishes.collectAsState(initial = listOf())

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(
                wishlist.value,
                key = { wish -> wish.id },
            ) { wish ->
                val dismissState = rememberDismissState(
                    confirmStateChange = {
                        if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                            viewModel.deleteWish(wish)
                        }
                        true
                    })

                SwipeToDismiss(
                    state = dismissState,
                    background = {
                        val color by animateColorAsState(
                            targetValue = if(dismissState.dismissDirection == DismissDirection.EndToStart){
                                Color.Red
                            } else {
                                Color.Transparent
                            },
                            label = "",
                        )

                        val alignment = Alignment.CenterEnd

                        Box(
                            Modifier.fillMaxSize().background(color).padding(horizontal =  20.dp),
                            contentAlignment = alignment
                        ){
                            Icon(Icons.Default.Delete, contentDescription = "delete icon", tint = Color.White)
                        }

                    },
                    directions = setOf(DismissDirection.EndToStart),
                    dismissThresholds = { FractionalThreshold(1f) },
                    dismissContent = {
                        WishItem(wish = wish) {
                            val id = wish.id
                            navController.navigate(Screen.AddScreen.route + "/$id")
                        }
                    }
                )
            }
        }
    }
}