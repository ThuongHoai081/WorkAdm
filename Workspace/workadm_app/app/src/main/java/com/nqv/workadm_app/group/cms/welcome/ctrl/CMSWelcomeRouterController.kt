package com.nqv.workadm_app.group.cms.welcome.ctrl

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument

sealed class CMSWelcomeRouterController (
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object Login: CMSWelcomeRouterController(
        route           = "Login_screen",
    )
    object Sign: CMSWelcomeRouterController(
        route           = "Sign_screen",
    )

    object Welcome: CMSWelcomeRouterController(
        route           = "Welcome_screen",
    )
}