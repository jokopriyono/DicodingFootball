package com.jokopriyono.dicodingfootball.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Players(
        @SerializedName("idPlayer")
        var idPlayer: String,

        @SerializedName("strPlayer")
        var strPlayer: String,

        @SerializedName("strDescriptionEN")
        var strDescriptionEN: String,

        @SerializedName("strHeight")
        var strHeight: String? = null,

        @SerializedName("strCutout")
        var strCutout: String? = null,

        @SerializedName("strWeight")
        var strWeight: String? = null,

        @SerializedName("strPosition")
        var strPosition: String? = null,

        @SerializedName("strThumb")
        var strThumb: String? = null,

        @SerializedName("strFanart1")
        var strFanart1: String? = null
) : Parcelable