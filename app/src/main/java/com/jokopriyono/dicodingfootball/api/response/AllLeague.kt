package com.jokopriyono.dicodingfootball.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AllLeague(
        @SerializedName("idLeague")
        val idLeague: String,

        @SerializedName("strLeague")
        val leagueName: String,

        @SerializedName("strSport")
        val sportName: String,

        @SerializedName("strLeagueAlternate")
        val leagueAlternate: String
) : Parcelable