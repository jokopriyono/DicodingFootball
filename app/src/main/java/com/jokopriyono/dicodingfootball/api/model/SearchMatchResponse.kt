package com.jokopriyono.dicodingfootball.api.model

import android.os.Parcelable
import com.jokopriyono.dicodingfootball.api.response.LastLeague
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchMatchResponse(val event: List<LastLeague>?) : Parcelable