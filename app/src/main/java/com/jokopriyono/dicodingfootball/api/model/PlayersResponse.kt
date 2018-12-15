package com.jokopriyono.dicodingfootball.api.model

import android.os.Parcelable
import com.jokopriyono.dicodingfootball.api.response.Players
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlayersResponse(val player: List<Players>) : Parcelable