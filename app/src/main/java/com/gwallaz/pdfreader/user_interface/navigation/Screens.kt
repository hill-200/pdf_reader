package com.gwallaz.pdfreader.user_interface.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.ui.graphics.vector.ImageVector
import com.gwallaz.pdfreader.R

sealed class Screens(
    val route: String,
    val title: String,
    val image: ImageVector
){

    object AllFiles : Screens("all_files","All Files",Icons.Outlined.List)
    object Recent : Screens("recent","Recent", Icons.Outlined.Add)
    object Favourite : Screens("favourite","Recent", Icons.Outlined.Favorite)
    object Tools : Screens("tools","Tools", Icons.Outlined.Menu)
}
