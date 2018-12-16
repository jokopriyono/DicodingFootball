package com.jokopriyono.dicodingfootball.feature.searchmatch

import com.jokopriyono.dicodingfootball.api.response.LastLeague

interface SearchMatchView {
    fun showData(events: List<LastLeague>?)
}