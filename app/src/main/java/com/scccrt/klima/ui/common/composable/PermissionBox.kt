package com.scccrt.klima.ui.common.composable

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.scccrt.klima.R

@Composable
fun PermissionBox(
    modifier: Modifier = Modifier,
    lottieResource: Int,
    permission: String,
    permissionHeader: String,
    permissionReason: String,
    contentAlignment: Alignment = Alignment.TopStart,
    onDismiss: () -> Unit,
    onGranted: @Composable () -> Unit,
) {
    PermissionBox(
        modifier,
        lottieResource,
        permissions = listOf(permission),
        requiredPermissions = listOf(permission),
        permissionHeader = permissionHeader,
        permissionReason = permissionReason,
        contentAlignment = contentAlignment,
        onDismiss = {
            onDismiss()
        }
    ) { onGranted() }
}

/**
 * A variation of [PermissionBox] that takes a list of permissions and only calls [onGranted] when
 * all the [requiredPermissions] are granted.
 *
 * By default it assumes that all [permissions] are required.
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionBox(
    modifier: Modifier = Modifier,
    lottieResource: Int,
    permissions: List<String>,
    requiredPermissions: List<String> = permissions,
    permissionHeader: String,
    permissionReason: String,
    contentAlignment: Alignment = Alignment.Center,
    onDismiss: () -> Unit,
    onGranted: @Composable () -> Unit,
) {
    Dialog(onDismissRequest = { onDismiss() }, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        var permissionDenied by remember { mutableStateOf(false) }

        val permissionState = rememberMultiplePermissionsState(permissions = permissions) { map ->
            val rejectedPermissions = map.filterValues { !it }.keys
            permissionDenied = rejectedPermissions.all { it in requiredPermissions }
        }
        val allRequiredPermissionsGranted =
            permissionState.revokedPermissions.none { it.permission in requiredPermissions }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .then(modifier),
            contentAlignment = if (allRequiredPermissionsGranted) {
                contentAlignment
            } else {
                Alignment.Center
            },
        ) {
            if (allRequiredPermissionsGranted) {
                onGranted()
            } else {
                PermissionScreen(
                    permissionState,
                    permissionHeader = permissionHeader,
                    permissionReason = permissionReason,
                    lottieResource = lottieResource,
                    permissionDenied = permissionDenied,
                ) {
                    onDismiss()
                }
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun PermissionScreen(
    state: MultiplePermissionsState,
    permissionHeader: String,
    permissionReason: String,
    lottieResource: Int,
    permissionDenied: Boolean,
    onClickCancel: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LottieBox(
            resourceId = lottieResource,
            modifier = Modifier
                .height(350.dp)
                .padding(16.dp)
        )

        Text(
            text = permissionHeader,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(16.dp),
        )

        Spacer(modifier = Modifier.size(4.dp))

        Text(
            text = permissionReason,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(48.dp),
        )

        Column(
            modifier = Modifier
                .padding(48.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    if (state.shouldShowRationale) {
                        // noop
                    } else {
                        if (permissionDenied) {
                            context.showAppSettings()
                        } else {
                            state.launchMultiplePermissionRequest()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = stringResource(R.string.request_permissions))
            }
            Button(
                onClick = { onClickCancel() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.dismiss))
            }
        }
    }
}

fun Context.showAppSettings() {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
        data = Uri.parse("package:${packageName}")
    }
    startActivity(intent)
}