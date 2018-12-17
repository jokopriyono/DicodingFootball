package com.jokopriyono.dicodingfootball.feature.favorite

import android.content.Context
import com.jokopriyono.dicodingfootball.api.response.LastLeague
import com.jokopriyono.dicodingfootball.api.response.Team
import com.jokopriyono.dicodingfootball.data.FavoriteMatch
import com.jokopriyono.dicodingfootball.data.FavoriteTeam
import com.jokopriyono.dicodingfootball.data.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoritePresenter(val view: FavoriteView) {
    fun loadData(context: Context) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val lastLeagues: MutableList<LastLeague> = mutableListOf()
                val teams: MutableList<Team> = mutableListOf()
                context.database.use {
                    val result = select(FavoriteMatch.TABLE_FAV_MATCH)
                    val match = result.parseList(classParser<FavoriteMatch>())
                    for (data: FavoriteMatch in match) {
                        val lastLeague = LastLeague(
                                data.id.toString(),
                                data.idEvent,
                                data.strEvent,
                                data.strFilename,
                                data.strLeague,
                                data.strHomeTeam,
                                data.strAwayTeam,
                                data.intHomeScore,
                                data.intAwayScore,
                                data.strHomeGoalDetails,
                                data.strHomeYellowCards,
                                data.strHomeRedCards,
                                data.strAwayGoalDetails,
                                data.strAwayYellowCards,
                                data.strAwayRedCards,
                                data.dateEvent,
                                data.strDate,
                                data.idHomeTeam,
                                data.idAwayTeam,
                                "Soccer")
                        lastLeagues.add(lastLeague)
                    }

                    val result2 = select(FavoriteTeam.TABLE_FAV)
                    val teamDb = result2.parseList(classParser<FavoriteTeam>())
                    for (t: FavoriteTeam in teamDb) {
                        val teamss = Team(
                                t.teamId,
                                t.teamName,
                                t.teamBadge,
                                t.teamLogo,
                                t.teamStadium,
                                t.teamDesc,
                                t.teamYear)
                        teams.add(teamss)
                    }

                    view.showAllFavorite(lastLeagues, teams)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}