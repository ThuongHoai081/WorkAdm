package com.nqv.workadm_app.group.cms.menu.ctrl

import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nqv.workadm_app.group.cms.menu.tmpl.BottomMenuItem
import com.nqv.workadm_app.main.route.RouteController

@Composable
fun BottomNavigationBar (
    navCtrl: NavHostController
) {
    val screens = listOf(
        RouteController.Home,
        RouteController.Projects,
        RouteController.Kanban,
        RouteController.Users,
        RouteController.Profile
    )

    val navBackStackEntry by navCtrl.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        screens.forEach { item ->
            BottomMenuItem(
                screen              =  item,
                currentDestination  = currentDestination,
                navCtrl             = navCtrl)
        }
    }
}
