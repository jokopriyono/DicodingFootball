package com.jokopriyono.dicodingfootball.feature.main

import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.ApiRepository
import com.jokopriyono.dicodingfootball.TeamResponse
import com.jokopriyono.dicodingfootball.TheSportDBApi
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
}