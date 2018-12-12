package com.jokopriyono.dicodingfootball.feature.main

import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.api.ApiRepository
import com.jokopriyono.dicodingfootball.api.TheSportDBApi
import com.jokopriyono.dicodingfootball.api.model.AllLeagueResponse
import com.jokopriyono.dicodingfootball.api.model.LastLeagueResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(val view: MainView, private val repo: ApiRepository, private val gson: Gson) {

    fun getAllLeague() {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(repo.doRequest(TheSportDBApi.getAllLeague()).await(), AllLeagueResponse::class.java)
            view.showSpinner(data.leagues)
        }
    }

    fun getLastLeague(idLeague: Int) {
        view.showLoading()
        GlobalScope.launch {
            val data = gson.fromJson(repo.doRequest(TheSportDBApi.getLastLeague(idLeague.toString())).await(), LastLeagueResponse::class.java)
            view.showLastLeague(data.events)
        }
    }

    fun getNextLeague(idLeague: Int) {
        view.showLoading()
        GlobalScope.launch {
            val data = gson.fromJson(repo.doRequest(TheSportDBApi.getNextLeague(idLeague.toString())).await(), LastLeagueResponse::class.java)
            view.showLastLeague(data.events)
        }
    }
}