package com.jokopriyono.dicodingfootball.feature.team

import com.jokopriyono.dicodingfootball.api.response.AllLeague
import com.jokopriyono.dicodingfootball.api.response.Team

interface ListTeamView {
    fun showSpinner(leagues: List<AllLeague>)
    fun showData(teams: List<Team>)
    fun showDataHideSpinner(teams: List<Team>)
}