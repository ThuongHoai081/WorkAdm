package com.nqv.workadm_app.group.cms.welcome.ctrl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nqv.workadm_app.group.cms.login.tmpl.Login_Content
import com.nqv.workadm_app.group.cms.login.tmpl.Sign_Content
import com.nqv.workadm_app.group.cms.login.tmpl.Welcome_Content
import com.nqv.workadm_app.group.cms.welcome.ctrl.CMSWelcomeRouterController


@Composable
fun CMSWelcomeController(
    navCtrl: NavHostController,
    onLoginSuccess: (Boolean) -> Unit
) {
    NavHost(
        navController       = navCtrl,
        startDestination    = CMSWelcomeRouterController.Welcome.route
    ) {
        composable(
            route = CMSWelcomeRouterController.Login.route
        ) {
            Login_Content(navCtrl = navCtrl, onLoginSuccess = { })
        }

        composable(
            route = CMSWelcomeRouterController.Sign.route
        ) {
            Sign_Content(navCtrl = navCtrl)
        }

        composable(
            route = CMSWelcomeRouterController.Welcome.route
        ) {
            Welcome_Content(navCtrl = navCtrl)
        }
    }
}