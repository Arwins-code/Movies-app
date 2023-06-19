package com.arwin.mymovieapps.screens.destinations

import androidx.annotation.RestrictTo
import androidx.compose.runtime.Composable
import com.arwin.mymovieapps.screens.home.search.SearchScreen
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import com.ramcosta.composedestinations.scope.DestinationScope

object SearchScreenDestination : DirectionDestination {
         
    operator fun invoke() = this
    
    @get:RestrictTo(RestrictTo.Scope.SUBCLASSES)
    override val baseRoute = "search_screen"

    override val route = baseRoute
    
    @Composable
    override fun DestinationScope<Unit>.Content(
		dependenciesContainerBuilder: @Composable DependenciesContainerBuilder<Unit>.() -> Unit
    ) {
		SearchScreen(
			navigator = destinationsNavigator
		)
    }
    
}