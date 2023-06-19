package com.arwin.mymovieapps.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.arwin.mymovieapps.screens.destinations.AccountScreenDestination
import com.arwin.mymovieapps.screens.destinations.Destination
import com.arwin.mymovieapps.screens.destinations.FavoritesScreenDestination
import com.arwin.mymovieapps.screens.destinations.HomeScreenDestination

sealed class BottomNavItem(
    val title: String,
    val icon: @Composable () -> Unit,
    val destination: Destination
) {
    object Home : BottomNavItem(
        "Home", {
            Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
        }, HomeScreenDestination
    )

    object Favorites : BottomNavItem(
        "Favorites", {
            Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favorites")
        }, FavoritesScreenDestination
    )

    object Account : BottomNavItem(
        "Account", {
            Icon(imageVector = Icons.Filled.Person, contentDescription = "Account")
        }, AccountScreenDestination
    )
}