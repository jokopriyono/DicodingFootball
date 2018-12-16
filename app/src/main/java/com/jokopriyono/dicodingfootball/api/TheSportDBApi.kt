package com.jokopriyono.dicodingfootball.api

import android.net.Uri
import com.jokopriyono.dicodingfootball.BuildConfig

object TheSportDBApi {
    private fun getBaseURL(): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .build()
                .toString()
    }

    fun getAllTeams(league: String): String {
        return Uri.parse(getBaseURL()).buildUpon()
                .appendPath("search_all_teams.php")
                .appendQueryParameter("l", league)
                .build()
                .toString()
    }

    fun searchTeam(teamName: String?): String {
        return Uri.parse(getBaseURL()).buildUpon()
                .appendPath("searchteams.php")
                .appendQueryParameter("t", teamName)
                .build()
                .toString()
    }

    fun getAllLeague(): String {
        return Uri.parse(getBaseURL()).buildUpon()
                .appendPath("all_leagues.php")
                .build()
                .toString()
    }

    fun getLastLeague(idLeague: String): String {
        return Uri.parse(getBaseURL()).buildUpon()
                .appendPath("eventspastleague.php")
                .appendQueryParameter("id", idLeague)
                .build()
                .toString()
    }

    fun getNextLeague(idLeague: String): String {
        return Uri.parse(getBaseURL()).buildUpon()
                .appendPath("eventsnextleague.php")
                .appendQueryParameter("id", idLeague)
                .build()
                .toString()
    }

    fun getPlayers(team: String): String {
        return Uri.parse(getBaseURL()).buildUpon()
                .appendPath("searchplayers.php")
                .appendQueryParameter("t", team)
                .build()
                .toString()
    }

    fun searchMatch(event: String): String {
        return Uri.parse(getBaseURL()).buildUpon()
                .appendPath("searchevents.php")
                .appendQueryParameter("e", event)
                .build()
                .toString()
    }
}