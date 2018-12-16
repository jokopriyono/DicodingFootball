package com.jokopriyono.dicodingfootball.feature.detailteam

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.R
import com.jokopriyono.dicodingfootball.api.ApiRepository
import com.jokopriyono.dicodingfootball.api.TheSportDBApi
import com.jokopriyono.dicodingfootball.api.model.PlayersResponse
import com.jokopriyono.dicodingfootball.api.response.Team
import com.jokopriyono.dicodingfootball.data.FavoriteTeam
import com.jokopriyono.dicodingfootball.data.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailTeamPresenter(val view: DetailTeamView, private val context: Context, private val repository: ApiRepository, val gson: Gson) {
    fun getPlayers(teamName: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(repository.doRequest(TheSportDBApi.getPlayers(teamName)).await(), PlayersResponse::class.java)
            view.showData(data)
        }
    }

    fun removeTeamDB(idTeam: String) {
        try {
            context.database.use {
                delete(FavoriteTeam.TABLE_FAV, "(${FavoriteTeam.TEAM_ID} = ${idTeam.toLong()})", null)
            }
            context.toast(R.string.removed_fav)
        } catch (e: SQLiteConstraintException) {
            context.toast(e.localizedMessage)
        }
    }

    fun addTeamDB(teams: Team) {
        try {
            context.database.use {
                insert(FavoriteTeam.TABLE_FAV,
                        FavoriteTeam.TEAM_ID to teams.teamId,
                        FavoriteTeam.TEAM_NAME to teams.teamName,
                        FavoriteTeam.TEAM_BADGE to teams.teamBadge,
                        FavoriteTeam.TEAM_LOGO to teams.urlTeamLogo,
                        FavoriteTeam.TEAM_DESC to teams.strDescriptionEN,
                        FavoriteTeam.TEAM_STADIUM to teams.strStadium,
                        FavoriteTeam.TEAM_YEAR to teams.year)
            }
            context.toast(R.string.added_fav)
        } catch (e: Exception) {
            context.toast(e.localizedMessage)
        }
    }

    fun selectTeamSB(idTeam: String) {
        context.database.use {
            val result = select(FavoriteTeam.TABLE_FAV)
                    .whereArgs("(${FavoriteTeam.TEAM_ID} = {id})", "id" to idTeam)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) view.setFavorite(true)
        }
    }
}