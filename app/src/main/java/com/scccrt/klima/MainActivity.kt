package com.scccrt.klima

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.res.stringResource
import com.scccrt.klima.ui.common.composable.PermissionBox
import com.scccrt.klima.ui.navigation.AppNavigation
import com.scccrt.klima.ui.theme.KlimaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val locationPermissions = listOf(
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KlimaTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    PermissionBox(
                        lottieResource = R.raw.lottie_location,
                        permissionHeader = stringResource(id = R.string.location_permission_header),
                        permissionReason = stringResource(id = R.string.location_permission_reason),
                        permissions = locationPermissions,
                        onDismiss = { finish() },
                        onGranted = { AppNavigation() }
                    )
                }
            }
        }
    }
}