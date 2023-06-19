package com.arwin.mymovieapps.screens

import com.arwin.mymovieapps.screens.destinations.AboutScreenDestination
import com.arwin.mymovieapps.screens.destinations.AccountScreenDestination
import com.arwin.mymovieapps.screens.destinations.CastsScreenDestination
import com.arwin.mymovieapps.screens.destinations.FavoritesScreenDestination
import com.arwin.mymovieapps.screens.destinations.HomeScreenDestination
import com.arwin.mymovieapps.screens.destinations.MovieDetailsScreenDestination
import com.arwin.mymovieapps.screens.destinations.SearchScreenDestination
import com.arwin.mymovieapps.screens.destinations.TvShowDetailsScreenDestination
import com.ramcosta.composedestinations.spec.*

/**
 * Class generated if any Composable is annotated with `@Destination`.
 * It aggregates all [TypedDestination]s in their [NavGraph]s.
 */
object NavGraphs {

    val root = NavGraph(
        route = "root",
        startRoute = HomeScreenDestination,
        destinations = listOf(
            AccountScreenDestination,
			AboutScreenDestination,
			CastsScreenDestination,
			FavoritesScreenDestination,
			HomeScreenDestination,
			MovieDetailsScreenDestination,
			TvShowDetailsScreenDestination,
			SearchScreenDestination
        )
    )
}