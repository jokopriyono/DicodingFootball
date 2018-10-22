package com.jokopriyono.dicodingfootball.feature.favorite

import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.api.ApiRepository

class FavoritePresenter(val view: FavoriteView, private val repo: ApiRepository, private val gson: Gson)