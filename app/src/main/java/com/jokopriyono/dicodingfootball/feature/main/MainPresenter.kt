package com.jokopriyono.dicodingfootball.feature.main

import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.api.ApiRepository
import com.jokopriyono.dicodingfootball.api.model.TeamResponse
import com.jokopriyono.dicodingfootball.api.TheSportDBApi
import com.jokopriyono.dicodingfootball.api.model.AllLeagueResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(val view: MainView, private val repo: ApiRepository, private val gson: Gson){
    fun getTeams(league: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(repo.doRequest(TheSportDBApi.getTeams(league)), TeamResponse::class.java)
            uiThread { view.hideLoading(); view.showTeams(data.teams) }
        }
    }

    fun getAllLeague(){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(repo.doRequest(TheSportDBApi.getAllLeague()), AllLeagueResponse::class.java)
            uiThread {
                view.hideLoading()
                view.showSpinner(data.leagues)
            }
        }
    }
}