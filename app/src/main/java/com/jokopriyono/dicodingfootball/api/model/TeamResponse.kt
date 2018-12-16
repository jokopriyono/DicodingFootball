package com.jokopriyono.dicodingfootball.api.model

import android.os.Parcelable
import com.jokopriyono.dicodingfootball.api.response.Team
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamResponse(val teams: List<Team>) : Parcelable