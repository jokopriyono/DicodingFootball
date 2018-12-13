package com.jokopriyono.dicodingfootball.feature.detailteam

import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.api.ApiRepository

class DetailTeamPresenter(val view: DetailTeamView, val repository: ApiRepository, val gson: Gson)