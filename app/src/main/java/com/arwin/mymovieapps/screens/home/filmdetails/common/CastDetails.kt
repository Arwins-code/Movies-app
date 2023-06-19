package com.arwin.mymovieapps.screens.home.filmdetails.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.arwin.mymovieapps.data.remote.responses.CreditResponse
import com.arwin.mymovieapps.screens.commons.CastItem
import com.arwin.mymovieapps.screens.destinations.CastsScreenDestination
import com.arwin.mymovieapps.util.Constant
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import timber.log.Timber

@Composable
fun CastDetails(
    creditsResponse: CreditResponse?,
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier
) {
    Column {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Cast",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(
                        start = 8.dp
                    )
            )

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
//                Text(
//                    text = "View all",
//                    fontWeight = FontWeight.ExtraLight,
//                    fontSize = 18.sp,
//                    color = Color.White
//                )

                IconButton(onClick = {
                    Timber.d("${creditsResponse == null}")

                    if (creditsResponse == null) {
                        return@IconButton
                    }
                    navigator.navigate(CastsScreenDestination(creditsResponse))
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowForward,
                        contentDescription = null
                    )
                }
            }
        }


        LazyRow(content = {
            items(creditsResponse?.cast!!) { cast ->
                CastItem(
                    size = 130.dp,
                    castImageUrl = "${Constant.IMAGE_BASE_URL}/${cast.profilePath}",
                    castName = cast.name
                )
            }
        })
    }
}