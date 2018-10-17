package com.jokopriyono.dicodingfootball.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LastLeague(
        @SerializedName("idEvent")
        val idEvent: String,

        @SerializedName("strEvent")
        val eventName: String,

        @SerializedName("strFilename")
        val eventFilename: String,

        @SerializedName("strLeague")
        val leagueName: String,

        @SerializedName("strHomeTeam")
        val homeTeamName: String,

        @SerializedName("strAwayTeam")
        val awayTeamName: String,

        @SerializedName("intHomeScore")
        val homeScore: String,

        @SerializedName("intAwayScore")
        val awayScore: String,

        @SerializedName("strHomeGoalDetails")
        val homeGoalDetails: String,

        @SerializedName("strHomeYellowCards")
        val homeYellowCards: String,

        @SerializedName("strHomeRedCards")
        val homeRedCards: String,

        @SerializedName("strAwayGoalDetails")
        val awayGoalDetails: String,

        @SerializedName("strAwayYellowCards")
        val awayYellowCards: String,

        @SerializedName("strAwayRedCards")
        val awayRedCards: String,

        @SerializedName("dateEvent")
        val dateEvent: String,

        @SerializedName("strDate")
        val strDate: String,

        @SerializedName("idHomeTeam")
        val idHomeTeam: String,

        @SerializedName("idAwayTeam")
        val idAwayTeam: String
) : Parcelable