package com.scccrt.klima.ui.common.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KlimaLocationManager @Inject constructor(
    @ApplicationContext val context: Context
) {

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }

    @SuppressLint("MissingPermission")
    fun locationUpdates(intervalInMillis: Long = DEFAULT_UPDATE_INTERVAL): Flow<Location> {
        return callbackFlow {
            if (!context.isNetworkOrGPSEnabled()) {
                throw LocationDisabledException
            }

            val request = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, intervalInMillis)
                .setWaitForAccurateLocation(false).build()

            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    super.onLocationResult(result)
                    result.locations.lastOrNull()?.let { location: Location ->
                        launch {
                            send(location)
                        }
                    }
                }
            }
            fusedLocationClient.requestLocationUpdates(request, locationCallback, Looper.getMainLooper())
            awaitClose {
                fusedLocationClient.removeLocationUpdates(locationCallback)
            }
        }
    }

    companion object {
        private const val DEFAULT_UPDATE_INTERVAL: Long = 1000L * 60 * 60
    }
}

object LocationDisabledException : Exception()

fun Context.isNetworkOrGPSEnabled(): Boolean {
    val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    return isGpsEnabled || isNetworkEnabled
}