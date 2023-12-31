package com.arwin.mymovieapps.screens.casts_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arwin.mymovieapps.data.remote.responses.CreditResponse
import com.arwin.mymovieapps.screens.commons.CastItem
import com.arwin.mymovieapps.screens.commons.MovplayToolbar
import com.arwin.mymovieapps.util.Constant
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import timber.log.Timber

@Destination
@Composable
fun CastsScreen(
    creditsResponse: CreditResponse,
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        MovplayToolbar(
            navigator = navigator,
            title = {
                Text(
                    text = "Casts",
                )
            },
            showBackArrow = true
        )

        Timber.d(creditsResponse.cast[0].profilePath)

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(creditsResponse.cast) { cast ->
                Timber.d(cast.toString())

                val imageLink = if (cast.profilePath.equals("") || cast.profilePath == null) {
                    "https://pixy.org/src/9/94083.png"
                } else {
                    cast.profilePath
                }

                CastItem(
                    size = 200.dp,
                    castImageUrl = "${Constant.IMAGE_BASE_URL}/$imageLink",
                    castName = cast.name
                )
            }
        }
    }
}