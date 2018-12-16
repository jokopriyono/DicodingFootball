package com.jokopriyono.dicodingfootball.api.model

import android.os.Parcelable
import com.jokopriyono.dicodingfootball.api.response.LastLeague
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LastLeagueResponse(val events: List<LastLeague>) : Parcelable