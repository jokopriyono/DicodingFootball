package com.jokopriyono.dicodingfootball.feature.detail

import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.api.ApiRepository

class DetailPresenter(val view: DetailView, private val repo: ApiRepository, private val gson: Gson)