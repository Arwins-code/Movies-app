package com.arwin.mymovieapps.screens.navtype

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavBackStackEntry
import com.arwin.mymovieapps.data.remote.responses.CreditResponse
import com.ramcosta.composedestinations.navargs.DestinationsNavType
import com.ramcosta.composedestinations.navargs.DestinationsNavTypeSerializer
import com.ramcosta.composedestinations.navargs.parcelable.DefaultParcelableNavTypeSerializer
import com.ramcosta.composedestinations.navargs.primitives.DECODED_NULL
import com.ramcosta.composedestinations.navargs.primitives.ENCODED_NULL
import com.ramcosta.composedestinations.navargs.utils.encodeForRoute

val creditResponseNavType = CreditResponseNavType(DefaultParcelableNavTypeSerializer(CreditResponse::class.java))

class CreditResponseNavType(
    private val stringSerializer: DestinationsNavTypeSerializer<Parcelable>
) : DestinationsNavType<CreditResponse?>() {

    override fun get(bundle: Bundle, key: String): CreditResponse? {
        return bundle.getParcelable(key)
    }

    override fun put(bundle: Bundle, key: String, value: CreditResponse?) {
        bundle.putParcelable(key, value)
    }

    override fun parseValue(value: String): CreditResponse? {
        return if (value == DECODED_NULL) {
            null
        } else {
            stringSerializer.fromRouteString(value) as CreditResponse       
        }
    }

    override fun serializeValue(value: CreditResponse?): String {
        return if (value == null) {
            ENCODED_NULL
        } else {
            encodeForRoute(stringSerializer.toRouteString(value))
        }
    }
    
    override fun get(navBackStackEntry: NavBackStackEntry, key: String): CreditResponse? {
        return navBackStackEntry.arguments?.getParcelable(key)
    }

    override fun get(savedStateHandle: SavedStateHandle, key: String): CreditResponse? {
        return savedStateHandle.get(key)
    }
}