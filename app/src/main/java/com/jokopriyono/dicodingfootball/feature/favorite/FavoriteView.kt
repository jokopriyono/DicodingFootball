package com.jokopriyono.dicodingfootball.feature.favorite

import com.jokopriyono.dicodingfootball.api.response.LastLeague
import com.jokopriyono.dicodingfootball.api.response.Team

interface FavoriteView {
    fun showAllFavorite(lastLeagues: MutableList<LastLeague>, teams: MutableList<Team>)
}