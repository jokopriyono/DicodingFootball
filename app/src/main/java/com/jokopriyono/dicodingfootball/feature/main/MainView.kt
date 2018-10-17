package com.jokopriyono.dicodingfootball.feature.main

import com.jokopriyono.dicodingfootball.api.response.AllLeague
import com.jokopriyono.dicodingfootball.api.response.LastLeague
import com.jokopriyono.dicodingfootball.api.response.Team

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showTeams(data: List<Team>)
    fun showSpinner(allLeague: List<AllLeague>)
    fun showLastLeague(events: List<LastLeague>)
}