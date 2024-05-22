package com.nqv.workadm_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nqv.workadm_app.group.cms.welcome.ctrl.CMSWelcomeController
import com.nqv.workadm_app.main.route.NavGraph
import com.nqv.workadm_app.ui.theme.Workadm_appTheme

class MainActivity : ComponentActivity() {
    private lateinit var navCtrl: NavHostController
    private var isLoggedIn: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Workadm_appTheme {
                navCtrl = rememberNavController()
                if (isLoggedIn) {
                    NavGraph(navCtrl = navCtrl)
                } else {
                    CMSWelcomeController(navCtrl = navCtrl) { loggedIn->isLoggedIn = loggedIn }
                }
            }
        }
    }
}