package com.nqv.workadm_app.group.cms.menu.tmpl

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.nqv.workadm_app.main.route.RouteController

// Bottom Menu
@Composable
fun RowScope.BottomMenuItem(
    screen              : RouteController,
    currentDestination  : NavDestination?,
    navCtrl             : NavHostController
) {
    NavigationBarItem(
        onClick = {
            navCtrl.navigate(route = screen.route)
        },

        label = {
            Text(text = screen.title)
        },

//                alwaysShowLabel = false,

        icon = {
            BadgedBox(
                badge = {
                    if (screen.badgeCount != null) {
                        Badge {
                            Text(text = screen.badgeCount.toString())
                        }
                    } else if (screen.hasNews) {
                        Badge()
                    }
                }
            ) {
                Icon(
                    imageVector         = if (currentDestination?.route == screen.route) screen.selectedIcon else screen.unselectedIcon,
                    contentDescription  = "Nav Icon"
                )
            }
        },

        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
    )
}

// Dropdown menu
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuBar() {
    val sampleOpts = listOf("Tasks", "Note", "Other")


    var isExpanded by remember {
        mutableStateOf(false)
    }

    var opts by remember {
        mutableStateOf(sampleOpts[0])
    }


    Box(
        contentAlignment = Alignment.TopEnd
    ) {
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it},
            modifier = Modifier.defaultMinSize()
        ) {
            TextField(
                value = opts,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                sampleOpts.forEach() {item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            opts = item
                            isExpanded = false
                        }
                    )
                }
            }
        }
    }
}