package com.nqv.workadm_app.main.route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SupervisorAccount
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AssignmentTurnedIn
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.SupervisorAccount
import androidx.compose.ui.graphics.vector.ImageVector

sealed class RouteController (
    val route           : String        ,
    val title           : String        ,
    val selectedIcon    : ImageVector   ,
    val unselectedIcon  : ImageVector   ,
    val hasNews         : Boolean       ,
    val badgeCount      : Int? = null
) {
    object Home: RouteController(
        route           = "Home_screen",
        title           = "Home",
        selectedIcon    = Icons.Filled.Home,
        unselectedIcon  = Icons.Outlined.Home,
        hasNews         = false
    )
    object Projects: RouteController(
        route           = "Projects_screen",
        title           = "Projects",
        selectedIcon    = Icons.Filled.Folder,
        unselectedIcon  = Icons.Outlined.Folder,
        hasNews         = false
    )
    object Kanban: RouteController(
        route           = "Kanban Board_screen",
        title           = "Tasks",
        selectedIcon    = Icons.Filled.AssignmentTurnedIn,
        unselectedIcon  = Icons.Outlined.AssignmentTurnedIn,
        hasNews         = true
    )
    object Users: RouteController(
        route           = "Users_screen",
        title           = "Users",
        selectedIcon    = Icons.Filled.SupervisorAccount,
        unselectedIcon  = Icons.Outlined.SupervisorAccount,
        hasNews         = false
    )
    object Profile: RouteController(
        route           = "Profile_screen",
        title           = "Profile",
        selectedIcon    = Icons.Filled.AccountCircle,
        unselectedIcon  = Icons.Outlined.AccountCircle,
        hasNews         = false
    )
}