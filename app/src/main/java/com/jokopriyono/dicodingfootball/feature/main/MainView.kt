package com.jokopriyono.dicodingfootball.feature.main

import com.jokopriyono.dicodingfootball.api.response.AllLeague
import com.jokopriyono.dicodingfootball.api.response.LastLeague

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showSpinner(allLeague: List<AllLeague>)
    fun showLastLeague(events: List<LastLeague>)
}