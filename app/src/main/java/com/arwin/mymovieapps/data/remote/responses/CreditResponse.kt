package com.arwin.mymovieapps.data.remote.responses

import android.os.Parcelable
import com.arwin.mymovieapps.model.Cast
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreditResponse(
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("id")
    val id: Int
) : Parcelable
