package com.arwin.mymovieapps.data.remote.responses

import com.arwin.mymovieapps.model.Genre
import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("genres")
    val genres: List<Genre>
)