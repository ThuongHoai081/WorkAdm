package com.nqv.workadm_app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nqv.workadm_app.group.cms.menu.ctrl.BottomNavigationBar
import com.nqv.workadm_app.group.cms.menu.tmpl.DropdownMenuBar
import com.nqv.workadm_app.group.kanban.ctrl.PreviewApp

@Composable
fun KanbanScreen (
    navCtrl: NavHostController
) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text        = "Kanban Board",
                        color       = Color.White ,
                        fontWeight  = FontWeight.Bold
                    )
                },

                actions = {
                    DropdownMenuBar()
                }
            )
        },

        bottomBar = {
            BottomNavigationBar(navCtrl)//này nè
        }
    ) {paddingValues ->

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            PreviewApp()
        }

    }
}