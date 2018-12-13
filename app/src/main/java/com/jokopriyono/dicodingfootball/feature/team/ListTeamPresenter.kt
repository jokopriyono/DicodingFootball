package com.jokopriyono.dicodingfootball.feature.team

import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.api.ApiRepository
import com.jokopriyono.dicodingfootball.api.TheSportDBApi
import com.jokopriyono.dicodingfootball.api.model.AllLeagueResponse
import com.jokopriyono.dicodingfootball.api.model.TeamResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListTeamPresenter(val view: ListTeamView, val repository: ApiRepository, val gson: Gson) {
    fun getAllLeague() {
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(repository.doRequest(TheSportDBApi.getAllLeague()).await(), AllLeagueResponse::class.java)
            view.showSpinner(data.leagues)
        }
    }

    fun getTeams(league: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(repository.doRequest(TheSportDBApi.getAllTeams(league)).await(), TeamResponse::class.java)
            view.showData(data.teams)
        }
    }

    fun searchTeams(teamName: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(repository.doRequest(TheSportDBApi.searchTeam(teamName)).await(), TeamResponse::class.java)
            view.showDataHideSpinner(data.teams)
        }
    }
}