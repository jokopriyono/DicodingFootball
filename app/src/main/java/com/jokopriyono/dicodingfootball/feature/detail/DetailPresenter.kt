package com.jokopriyono.dicodingfootball.feature.detail

import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.api.ApiRepository
import com.jokopriyono.dicodingfootball.api.TheSportDBApi
import com.jokopriyono.dicodingfootball.api.model.TeamResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailPresenter(val view: DetailView, private val repo: ApiRepository, private val gson: Gson) {
    fun getTwoTeams(team1: String?, team2: String?) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val dataHome = gson.fromJson(repo.doRequest(TheSportDBApi.getTeam(team1)).await(), TeamResponse::class.java)
            val dataAway = gson.fromJson(repo.doRequest(TheSportDBApi.getTeam(team2)).await(), TeamResponse::class.java)
            view.hideLoading()
            view.showTeams(dataHome.teams[0], dataAway.teams[0])
        }
    }
}