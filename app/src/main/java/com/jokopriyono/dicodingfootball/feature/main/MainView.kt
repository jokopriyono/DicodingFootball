package com.jokopriyono.dicodingfootball.feature.main

import com.jokopriyono.dicodingfootball.Team

interface MainView{
    fun showLoading()
    fun hideLoading()
    fun showTeams(data: List<Team>)
}