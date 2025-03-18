package kalbe.corp.locationapp.utils

import android.Manifest.permission
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class LocationUtils(
    val context: Context
) {
    fun hasLocationPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context, permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
            context, permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
}