package com.jokopriyono.dicodingfootball.feature.searchmatch

import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.api.ApiRepository

class SearchMatchPresenter(val view: SearchMatchView, val repository: ApiRepository, val gson: Gson)