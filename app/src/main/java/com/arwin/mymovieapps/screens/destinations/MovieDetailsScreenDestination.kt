package com.arwin.mymovieapps.screens.destinations

import androidx.annotation.RestrictTo
import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.navArgument
import com.arwin.mymovieapps.screens.destinations.MovieDetailsScreenDestination.NavArgs
import com.arwin.mymovieapps.screens.home.filmdetails.movie.MovieDetailsScreen
import com.ramcosta.composedestinations.navargs.primitives.DestinationsIntNavType
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import com.ramcosta.composedestinations.scope.DestinationScope
import com.ramcosta.composedestinations.spec.Direction

object MovieDetailsScreenDestination : TypedDestination<NavArgs> {
    
    override fun invoke(navArgs: NavArgs): Direction = with(navArgs) {
        invoke(movieId)
    }
     
    operator fun invoke(
		movieId: Int,
    ): Direction {
        return object : Direction {
            override val route = "$baseRoute" +
					"/${DestinationsIntNavType.serializeValue(movieId)}"
        }
    }
    
    @get:RestrictTo(RestrictTo.Scope.SUBCLASSES)
    override val baseRoute = "movie_details_screen"

    override val route = "$baseRoute/{movieId}"
    
	override val arguments get() = listOf(
		navArgument("movieId") {
			type = DestinationsIntNavType
		}
	)

    @Composable
    override fun DestinationScope<NavArgs>.Content(
		dependenciesContainerBuilder: @Composable DependenciesContainerBuilder<NavArgs>.() -> Unit
    ) {
		val (movieId) = navArgs
		MovieDetailsScreen(
			movieId = movieId, 
			navigator = destinationsNavigator
		)
    }
                    
	override fun argsFrom(navBackStackEntry: NavBackStackEntry): NavArgs {
	    return NavArgs(
			movieId = DestinationsIntNavType.get(navBackStackEntry, "movieId") ?: throw RuntimeException("'movieId' argument is mandatory, but was not present!"),
	    )
	}
                
	override fun argsFrom(savedStateHandle: SavedStateHandle): NavArgs {
	    return NavArgs(
			movieId = DestinationsIntNavType.get(savedStateHandle, "movieId") ?: throw RuntimeException("'movieId' argument is mandatory, but was not present!"),
	    )
	}

	data class NavArgs(
		val movieId: Int,
	)
}