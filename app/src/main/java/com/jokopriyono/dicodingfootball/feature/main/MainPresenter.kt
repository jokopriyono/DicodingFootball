package com.jokopriyono.dicodingfootball.feature.main

import android.util.Log
import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.api.ApiRepository
import com.jokopriyono.dicodingfootball.api.model.TeamResponse
import com.jokopriyono.dicodingfootball.api.TheSportDBApi
import com.jokopriyono.dicodingfootball.api.model.AllLeagueResponse
import com.jokopriyono.dicodingfootball.api.model.LastLeagueResponse
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

    fun getLastTeam(idLeague: Int) {
        view.showLoading()
        doAsync {
            Log.d("pesan", TheSportDBApi.getLastLeague(idLeague.toString()))
            val data = gson.fromJson(repo.doRequest(TheSportDBApi.getLastLeague(idLeague.toString())), LastLeagueResponse::class.java)
            uiThread {
                view.hideLoading()
                view.showLastLeague(data.events)
            }
        }
    }
}