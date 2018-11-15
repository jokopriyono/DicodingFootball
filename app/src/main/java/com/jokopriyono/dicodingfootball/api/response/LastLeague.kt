package com.jokopriyono.dicodingfootball.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LastLeague (
        @SerializedName("idDatabase")
        val idDB: String? = null,

        @SerializedName("idEvent")
        val idEvent: String? = null,

        @SerializedName("strEvent")
        val eventName: String? = null,

        @SerializedName("strFilename")
        val eventFilename: String? = null,

        @SerializedName("strLeague")
        val leagueName: String? = null,

        @SerializedName("strHomeTeam")
        val homeTeamName: String? = null,

        @SerializedName("strAwayTeam")
        val awayTeamName: String? = null,

        @SerializedName("intHomeScore")
        val homeScore: String? = null,

        @SerializedName("intAwayScore")
        val awayScore: String? = null,

        @SerializedName("strHomeGoalDetails")
        val homeGoalDetails: String? = null,

        @SerializedName("strHomeYellowCards")
        val homeYellowCards: String? = null,

        @SerializedName("strHomeRedCards")
        val homeRedCards: String? = null,

        @SerializedName("strAwayGoalDetails")
        val awayGoalDetails: String? = null,

        @SerializedName("strAwayYellowCards")
        val awayYellowCards: String? = null,

        @SerializedName("strAwayRedCards")
        val awayRedCards: String? = null,

        @SerializedName("dateEvent")
        val dateEvent: String? = null,

        @SerializedName("strDate")
        val strDate: String? = null,

        @SerializedName("idHomeTeam")
        val idHomeTeam: String? = null,

        @SerializedName("idAwayTeam")
        val idAwayTeam: String? = null
) : Parcelable