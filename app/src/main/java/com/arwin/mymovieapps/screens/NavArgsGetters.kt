package com.arwin.mymovieapps.screens

import androidx.lifecycle.SavedStateHandle
import com.arwin.mymovieapps.screens.destinations.CastsScreenDestination
import com.arwin.mymovieapps.screens.destinations.MovieDetailsScreenDestination
import com.arwin.mymovieapps.screens.destinations.TvShowDetailsScreenDestination

inline fun <reified T> SavedStateHandle.navArgs(): T {
    return navArgs(T::class.java, this)
}

@Suppress("UNCHECKED_CAST")
fun <T> navArgs(argsClass: Class<T>, savedStateHandle: SavedStateHandle): T {
    return when (argsClass) {
		CastsScreenDestination.NavArgs::class.java -> CastsScreenDestination.argsFrom(savedStateHandle) as T
		MovieDetailsScreenDestination.NavArgs::class.java -> MovieDetailsScreenDestination.argsFrom(savedStateHandle) as T
		TvShowDetailsScreenDestination.NavArgs::class.java -> TvShowDetailsScreenDestination.argsFrom(savedStateHandle) as T
        else -> error("Class $argsClass is not a navigation arguments class!")
    }
}
