package com.nqv.workadm_app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.nqv.workadm_app.group.cms.menu.ctrl.BottomNavigationBar

@Composable
fun ProjectsScreen (
    navCtrl: NavHostController
) {
    Scaffold (
        bottomBar = {
            BottomNavigationBar(navCtrl)
        }
    ) {paddingValues ->

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Projects")
        }

    }
}