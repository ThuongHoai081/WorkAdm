package com.nqv.workadm_app.main.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nqv.workadm_app.HomeScreen
import com.nqv.workadm_app.KanbanScreen
import com.nqv.workadm_app.ProfileScreen
import com.nqv.workadm_app.ProjectsScreen
import com.nqv.workadm_app.UsersScreen


@Composable
fun NavGraph(
    navCtrl: NavHostController
) {
    NavHost(
        navController       = navCtrl,
        startDestination    = RouteController.Users.route
    ) {
        composable(
            route = RouteController.Home.route
        ) {
            HomeScreen(navCtrl = navCtrl)
        }

        composable(
            route = RouteController.Projects.route
        ) {
            ProjectsScreen(navCtrl = navCtrl)
        }

        composable(
            route = RouteController.Kanban.route
        ) {
            KanbanScreen(navCtrl = navCtrl)
        }

        composable(
            route = RouteController.Users.route
        ) {
            UsersScreen(navCtrl = navCtrl)
        }

        composable(
            route = RouteController.Profile.route
        ) {
            ProfileScreen(navCtrl = navCtrl)
        }
    }
}