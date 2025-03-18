package kalbe.corp.tipcalculator

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kalbe.corp.tipcalculator.ui.theme.TipCalculatorTheme
import kotlin.math.ceil
import kotlin.math.round
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TipCalculator()
                }
            }
        }
    }
}

@Composable
fun TipCalculator(){
    var roundUpChecked by remember { mutableStateOf(false) }
    var billAmountValue by remember { mutableStateOf("") }
    var calculatedTipValue by remember { mutableStateOf("") }
    var totalAmountValue by remember { mutableStateOf("") }
    val percentageValue = remember { mutableStateOf(5) }
    var iExpanded by remember { mutableStateOf(false) }

    fun calculateTip(){
        val billAmountDoubleValue = billAmountValue.toDoubleOrNull() ?: 0.00
        val result = round((billAmountDoubleValue * (percentageValue.value / 100.00)) * 100) / 100
        calculatedTipValue = result.toString()
    }

    fun calculateTotalAmount(){
        val billAmountDoubleValue = billAmountValue.toDoubleOrNull() ?: 0.00
        val result = round((billAmountDoubleValue + calculatedTipValue.toDouble()) * 100) / 100
        totalAmountValue = result.toString()
    }

    fun roundUpValues() {
        if (roundUpChecked) {
            calculatedTipValue = calculatedTipValue.toDoubleOrNull()?.let {
                ceil(it).roundToInt().toString()
            } ?: "0"

            totalAmountValue = totalAmountValue.toDoubleOrNull()?.let {
                ceil(it).roundToInt().toString()
            } ?: "0"
        } else {
            calculateTip()
            calculateTotalAmount()
        }
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text("TIP Calculator")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = billAmountValue,
            onValueChange = {
                billAmountValue = it
                calculateTip()
                calculateTotalAmount()
                roundUpValues()
            },
            label = { Text("Bill amount")})

        Spacer(modifier = Modifier.height(16.dp))

        Box {
            Button(onClick = { iExpanded = true }) {
                Text("${percentageValue.value} %")
                Icon(
                    Icons.Default.ArrowDropDown, contentDescription = "Dropdown arrow"
                )
            }

            DropdownMenu(
                expanded = iExpanded,
                onDismissRequest = { iExpanded = false }) {
                DropdownMenuItem(text = { Text("5%")}, onClick = {
                    percentageValue.value = 5
                    iExpanded = false
                    calculateTip()
                    calculateTotalAmount()
                    roundUpValues()
                })
                DropdownMenuItem(text = { Text("10%")}, onClick = {
                    percentageValue.value = 10
                    iExpanded = false
                    calculateTip()
                    calculateTotalAmount()
                    roundUpValues()
                })
                DropdownMenuItem(text = { Text("15%")}, onClick = {
                    percentageValue.value = 15
                    iExpanded = false
                    calculateTip()
                    calculateTotalAmount()
                    roundUpValues()
                })
                DropdownMenuItem(text = { Text("20%")}, onClick = {
                    percentageValue.value = 20
                    iExpanded = false
                    calculateTip()
                    calculateTotalAmount()
                    roundUpValues()
                })
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row (
            verticalAlignment = Alignment.CenterVertically

        ){
            Text("Round Up")
            Spacer(modifier = Modifier.width(16.dp))
            Switch(
                checked = roundUpChecked,
                onCheckedChange = {
                    roundUpChecked = !roundUpChecked
                    roundUpValues()
                })
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Calculated tip : $ $calculatedTipValue")
        Text("Total amount : $ $totalAmountValue")
    }
}

@Preview(showBackground = true)
@Composable
fun TipCalculatorPreview(){
    TipCalculator()
}