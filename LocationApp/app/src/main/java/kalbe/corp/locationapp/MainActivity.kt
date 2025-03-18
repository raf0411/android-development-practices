package kalbe.corp.locationapp

import android.Manifest.permission
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import kalbe.corp.locationapp.ui.theme.LocationAppTheme
import kalbe.corp.locationapp.utils.LocationUtils

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: LocationViewModel = viewModel()

            LocationAppTheme {
                MyApp(viewModel)
            }
        }
    }
}

@Composable
fun MyApp(viewModel: LocationViewModel) {
    val context: Context = LocalContext.current
    val locationUtils = LocationUtils(context)

    LocationDisplay(
        locationUtils = locationUtils,
        context = context,
        locationViewModel = viewModel,
    )
}

@Composable
fun LocationDisplay(
    locationUtils: LocationUtils,
    context: Context,
    locationViewModel: LocationViewModel,
) {
    val location = locationViewModel.location.value
    val address = location?.let {
        locationUtils.reverseGeocodeLocation(location)
    }

    val reqPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(), onResult = { permissions ->
            if (permissions[permission.ACCESS_FINE_LOCATION] == true || permissions[permission.ACCESS_COARSE_LOCATION] == true) {
                // LOCATION ACCESS GRANTED
                locationUtils.requestLocationUpdates(locationViewModel)
            } else {
                // ASK FOR PERMISSION
                val rationaleRequired = ActivityCompat.shouldShowRequestPermissionRationale(
                    context as MainActivity, permission.ACCESS_FINE_LOCATION
                ) || ActivityCompat.shouldShowRequestPermissionRationale(
                    context, permission.ACCESS_COARSE_LOCATION
                )

                if (rationaleRequired) {
                    Toast.makeText(
                        context,
                        "Location Permission is required for this feature to worked",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        context, "Go to settings and turn on Location Permission", Toast.LENGTH_LONG
                    ).show()
                }
            }
        })

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        if (location != null) {
            Log.d("LocationValue", location.toString())
            Text("Address: ${location.latitude} ${location.longitude} \n $address")
        } else {
            Text(text = "Location not available!")
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                Log.d("LocationValue", location.toString())

                if (locationUtils.hasLocationPermission(context)) {
                    // Permission already granted update the location
                    locationUtils.requestLocationUpdates(viewModel = locationViewModel)
                } else {
                    reqPermissionLauncher.launch(
                        arrayOf(
                            permission.ACCESS_COARSE_LOCATION, permission.ACCESS_FINE_LOCATION
                        )
                    )
                }
            },
        ) {
            Text("Get Location")
        }
    }
}