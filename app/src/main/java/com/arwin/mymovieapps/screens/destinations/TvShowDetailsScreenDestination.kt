package com.arwin.mymovieapps.screens.destinations

import androidx.annotation.RestrictTo
import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.navArgument
import com.arwin.mymovieapps.screens.destinations.TvShowDetailsScreenDestination.NavArgs
import com.arwin.mymovieapps.screens.home.filmdetails.tvshow.TvShowDetailsScreen
import com.ramcosta.composedestinations.navargs.primitives.DestinationsIntNavType
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import com.ramcosta.composedestinations.scope.DestinationScope
import com.ramcosta.composedestinations.spec.Direction

object TvShowDetailsScreenDestination : TypedDestination<NavArgs> {
    
    override fun invoke(navArgs: NavArgs): Direction = with(navArgs) {
        invoke(filmId)
    }
     
    operator fun invoke(
		filmId: Int,
    ): Direction {
        return object : Direction {
            override val route = "$baseRoute" +
					"/${DestinationsIntNavType.serializeValue(filmId)}"
        }
    }
    
    @get:RestrictTo(RestrictTo.Scope.SUBCLASSES)
    override val baseRoute = "tv_show_details_screen"

    override val route = "$baseRoute/{filmId}"
    
	override val arguments get() = listOf(
		navArgument("filmId") {
			type = DestinationsIntNavType
		}
	)

    @Composable
    override fun DestinationScope<NavArgs>.Content(
		dependenciesContainerBuilder: @Composable DependenciesContainerBuilder<NavArgs>.() -> Unit
    ) {
		val (filmId) = navArgs
		TvShowDetailsScreen(
			filmId = filmId, 
			navigator = destinationsNavigator
		)
    }
                    
	override fun argsFrom(navBackStackEntry: NavBackStackEntry): NavArgs {
	    return NavArgs(
			filmId = DestinationsIntNavType.get(navBackStackEntry, "filmId") ?: throw RuntimeException("'filmId' argument is mandatory, but was not present!"),
	    )
	}
                
	override fun argsFrom(savedStateHandle: SavedStateHandle): NavArgs {
	    return NavArgs(
			filmId = DestinationsIntNavType.get(savedStateHandle, "filmId") ?: throw RuntimeException("'filmId' argument is mandatory, but was not present!"),
	    )
	}

	data class NavArgs(
		val filmId: Int,
	)
}