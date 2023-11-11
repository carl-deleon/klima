package com.scccrt.klima.ui.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun KlimaBottomNavigationBar(
    bottomNavigationItems: List<BottomNavScreen>,
    selectedItem: Int,
    onItemClick: (BottomNavScreen, Int) -> Unit
) {
    NavigationBar {
        bottomNavigationItems.forEachIndexed { index, bottomNavScreen ->
            NavigationBarItem(
                selected = index == selectedItem,
                onClick = {
                    onItemClick(bottomNavScreen, index)
                },
                label = { Text(text = stringResource(id = bottomNavScreen.resourceId)) },
                icon = {
                    Icon(
                        painter = painterResource(id = bottomNavScreen.icon),
                        contentDescription = bottomNavScreen.route,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )
        }
    }
}