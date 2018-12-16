package com.jokopriyono.dicodingfootball.data

data class FavoriteTeam(val id: Long?,
                        val teamId: String?,
                        val teamName: String?,
                        val teamBadge: String?,
                        val teamLogo: String?,
                        val teamDesc: String?,
                        val teamStadium: String?,
                        val teamYear: String?) {
    companion object {
        const val TABLE_FAV: String = "T_FAVORITE"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_BADGE: String = "TEAM_BADGE"
        const val TEAM_LOGO: String = "TEAM_LOGO"
        const val TEAM_DESC: String = "TEAM_DESC"
        const val TEAM_STADIUM: String = "TEAM_STADIUM"
        const val TEAM_YEAR: String = "TEAM_YEAR"
    }
}