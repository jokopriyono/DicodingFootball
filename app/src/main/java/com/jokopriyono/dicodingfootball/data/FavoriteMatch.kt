package com.jokopriyono.dicodingfootball.data

data class FavoriteMatch(val id: Long?,
                         val idEvent: String?,
                         val strEvent: String?,
                         val strFilename: String?,
                         val strLeague: String?,
                         val strHomeTeam: String?,
                         val strAwayTeam: String?,
                         val intHomeScore: String?,
                         val intAwayScore: String?,
                         val strHomeGoalDetails: String?,
                         val strAwayGoalDetails: String?,
                         val strHomeRedCards: String?,
                         val strAwayRedCards: String?,
                         val strHomeYellowCards: String?,
                         val strAwayYellowCards: String?,
                         val dateEvent: String?,
                         val strDate: String?,
                         val idHomeTeam: String?,
                         val idAwayTeam: String?){
    companion object {
        const val TABLE_FAV_MATCH: String = "TABLE_MATCH"
        const val ID: String = "ID_"
        const val idEvent: String = "idEvent"
        const val strEvent: String = "strEvent"
        const val strFilename: String = "strFilename"
        const val strLeague: String = "strLeague"
        const val strHomeTeam: String = "strHomeTeam"
        const val strAwayTeam: String = "strAwayTeam"
        const val intHomeScore: String = "intHomeScore"
        const val intAwayScore: String = "intAwayScore"
        const val strHomeGoalDetails: String = "strHomeGoalDetails"
        const val strAwayGoalDetails: String = "strAwayGoalDetails"
        const val strHomeRedCards: String = "strHomeRedCards"
        const val strAwayRedCards: String = "strAwayRedCards"
        const val strHomeYellowCards: String = "strHomeYellowCards"
        const val strAwayYellowCards: String = "strAwayYellowCards"
        const val dateEvent: String = "dateEvent"
        const val strDate: String = "strDate"
        const val idHomeTeam: String = "idHomeTeam"
        const val idAwayTeam: String = "idAwayTeam"
    }
}