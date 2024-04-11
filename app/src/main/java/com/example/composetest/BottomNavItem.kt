package com.example.composetest

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    var title: String, val icon: ImageVector, val screenRoute: String
){
    data object HOME : BottomNavItem("home", Icons.Default.Home, "Home")
    data object EDIT : BottomNavItem("edit", Icons.Default.Edit, "edit")
    data object FAVORITE : BottomNavItem("favorite", Icons.Default.Favorite, "favorite")
    data object BUILD : BottomNavItem("build", Icons.Default.Build, "build")
    data object ACCOUNTBOX : BottomNavItem("accountbox", Icons.Default.AccountBox, "account")

}