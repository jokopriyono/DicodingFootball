package com.jokopriyono.dicodingfootball.feature.detail

import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.api.ApiRepository
import com.jokopriyono.dicodingfootball.TeamResponse
import com.jokopriyono.dicodingfootball.api.TheSportDBApi
import org.jetbrains.anko.doAsync

class DetailPresenter(val view: DetailView, private val repo: ApiRepository, private val gson: Gson){
    fun getTeams(league: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(repo.doRequest(TheSportDBApi.getTeams(league)), TeamResponse::class.java)
        }
    }
}