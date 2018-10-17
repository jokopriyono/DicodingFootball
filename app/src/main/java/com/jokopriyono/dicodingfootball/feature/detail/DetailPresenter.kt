package com.jokopriyono.dicodingfootball.feature.detail

import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.api.ApiRepository
import com.jokopriyono.dicodingfootball.api.TheSportDBApi
import com.jokopriyono.dicodingfootball.api.model.TeamResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailPresenter(val view: DetailView, private val repo: ApiRepository, private val gson: Gson){
    fun getTwoTeams(team1: String?, team2: String?) {
        view.showLoading()
        doAsync {
            val dataHome = gson.fromJson(repo.doRequest(TheSportDBApi.getTeam(team1)), TeamResponse::class.java)
            val dataAway = gson.fromJson(repo.doRequest(TheSportDBApi.getTeam(team2)), TeamResponse::class.java)
            uiThread {
                view.hideLoading()
                view.showTeams(dataHome.teams[0], dataAway.teams[0])
            }
        }
    }
}