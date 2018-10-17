package com.jokopriyono.dicodingfootball.feature.detail

import com.jokopriyono.dicodingfootball.api.response.Team

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeams(dataHome: Team?, dataAway: Team?)
}