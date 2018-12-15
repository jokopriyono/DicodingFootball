package com.jokopriyono.dicodingfootball.feature.detailteam

import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.api.ApiRepository
import com.jokopriyono.dicodingfootball.api.TheSportDBApi
import com.jokopriyono.dicodingfootball.api.model.PlayersResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailTeamPresenter(val view: DetailTeamView, private val repository: ApiRepository, val gson: Gson) {
    fun getPlayers(teamName: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(repository.doRequest(TheSportDBApi.getPlayers(teamName)).await(), PlayersResponse::class.java)
            view.showData(data)
        }
    }
}