package com.arwin.mymovieapps.screens.destinations

import androidx.annotation.RestrictTo
import androidx.compose.runtime.Composable
import com.arwin.mymovieapps.screens.account.about.AboutScreen
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import com.ramcosta.composedestinations.scope.DestinationScope

object AboutScreenDestination : DirectionDestination {
         
    operator fun invoke() = this
    
    @get:RestrictTo(RestrictTo.Scope.SUBCLASSES)
    override val baseRoute = "about_screen"

    override val route = baseRoute

    @Composable
    override fun DestinationScope<Unit>.Content(
		dependenciesContainerBuilder: @Composable DependenciesContainerBuilder<Unit>.() -> Unit
    ) {
		AboutScreen(
			navigator = destinationsNavigator
		)
    }
    
}