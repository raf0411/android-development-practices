package kalbe.corp.billsplitterapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class BillItem(val id : Int, var name : String, var price : Double)

@Composable
fun BillSplitterApp(){
    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center
    ) {
        Text("Bill Splitter")

        Button(onClick = { /*TODO*/ }) {
            Text("Add Bill")
            Icon(Icons.Default.Add, contentDescription = "plus icon")
        }
        
        LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)){

        }
    }
}