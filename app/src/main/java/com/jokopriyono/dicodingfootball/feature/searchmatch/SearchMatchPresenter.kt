package com.jokopriyono.dicodingfootball.feature.searchmatch

import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.api.ApiRepository
import com.jokopriyono.dicodingfootball.api.TheSportDBApi
import com.jokopriyono.dicodingfootball.api.model.SearchMatchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchMatchPresenter(val view: SearchMatchView, private val repository: ApiRepository, val gson: Gson) {
    fun searchMatch(match: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(repository.doRequest(TheSportDBApi.searchMatch(match)).await(), SearchMatchResponse::class.java)
            view.showData(data.event)
        }
    }
}