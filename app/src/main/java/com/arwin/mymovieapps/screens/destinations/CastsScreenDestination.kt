package com.arwin.mymovieapps.screens.destinations

import androidx.annotation.RestrictTo
import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.navArgument
import com.arwin.mymovieapps.data.remote.responses.CreditResponse
import com.arwin.mymovieapps.screens.casts_list.CastsScreen
import com.arwin.mymovieapps.screens.destinations.CastsScreenDestination.NavArgs
import com.arwin.mymovieapps.screens.navtype.creditResponseNavType
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import com.ramcosta.composedestinations.scope.DestinationScope
import com.ramcosta.composedestinations.spec.Direction

object CastsScreenDestination : TypedDestination<NavArgs> {
    
    override fun invoke(navArgs: NavArgs): Direction = with(navArgs) {
        invoke(creditsResponse)
    }
     
    operator fun invoke(
		creditsResponse: CreditResponse,
    ): Direction {
        return object : Direction {
            override val route = "$baseRoute" +
					"/${creditResponseNavType.serializeValue(creditsResponse)}"
        }
    }
    
    @get:RestrictTo(RestrictTo.Scope.SUBCLASSES)
    override val baseRoute = "casts_screen"

    override val route = "$baseRoute/{creditsResponse}"
    
	override val arguments get() = listOf(
		navArgument("creditsResponse") {
			type = creditResponseNavType
		}
	)

    @Composable
    override fun DestinationScope<NavArgs>.Content(
		dependenciesContainerBuilder: @Composable DependenciesContainerBuilder<NavArgs>.() -> Unit
    ) {
		val (creditsResponse) = navArgs
		CastsScreen(
			creditsResponse = creditsResponse, 
			navigator = destinationsNavigator
		)
    }
                    
	override fun argsFrom(navBackStackEntry: NavBackStackEntry): NavArgs {
	    return NavArgs(
			creditsResponse = creditResponseNavType.get(navBackStackEntry, "creditsResponse") ?: throw RuntimeException("'creditsResponse' argument is mandatory, but was not present!"),
	    )
	}
                
	override fun argsFrom(savedStateHandle: SavedStateHandle): NavArgs {
	    return NavArgs(
			creditsResponse = creditResponseNavType.get(savedStateHandle, "creditsResponse") ?: throw RuntimeException("'creditsResponse' argument is mandatory, but was not present!"),
	    )
	}

	data class NavArgs(
		val creditsResponse: CreditResponse,
	)
}