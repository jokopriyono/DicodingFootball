package com.jokopriyono.dicodingfootball.feature.detailteam

import com.jokopriyono.dicodingfootball.api.model.PlayersResponse

interface DetailTeamView {
    fun showData(players: PlayersResponse)
    fun setFavorite(favorite: Boolean)
}