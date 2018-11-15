package com.jokopriyono.dicodingfootball.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DBHelper(context: Context) : ManagedSQLiteOpenHelper(context, "Football.db", null, 1) {
    companion object {
        var instance: DBHelper? = null

        @Synchronized
        fun getInstance(context: Context): DBHelper {
            if (instance == null)
                instance = DBHelper(context.applicationContext)
            return instance as DBHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(FavoriteTeam.TABLE_FAV, true,
                FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteTeam.TEAM_ID to TEXT + UNIQUE,
                FavoriteTeam.TEAM_NAME to TEXT,
                FavoriteTeam.TEAM_BADGE to TEXT)
        db.createTable(FavoriteMatch.TABLE_FAV_MATCH, true,
                FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteMatch.idEvent to TEXT,
                FavoriteMatch.strEvent to TEXT,
                FavoriteMatch.strFilename to TEXT,
                FavoriteMatch.strLeague to TEXT,
                FavoriteMatch.strHomeTeam to TEXT,
                FavoriteMatch.strAwayTeam to TEXT,
                FavoriteMatch.intHomeScore to TEXT,
                FavoriteMatch.intAwayScore to TEXT,
                FavoriteMatch.strHomeGoalDetails to TEXT,
                FavoriteMatch.strAwayGoalDetails to TEXT,
                FavoriteMatch.strHomeRedCards to TEXT,
                FavoriteMatch.strAwayRedCards to TEXT,
                FavoriteMatch.strHomeYellowCards to TEXT,
                FavoriteMatch.strAwayYellowCards to TEXT,
                FavoriteMatch.dateEvent to TEXT,
                FavoriteMatch.strDate to TEXT,
                FavoriteMatch.idHomeTeam to TEXT,
                FavoriteMatch.idAwayTeam to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavoriteTeam.TABLE_FAV, true)
        db.dropTable(FavoriteMatch.TABLE_FAV_MATCH, true)
    }
}

val Context.database: DBHelper
    get() = DBHelper.getInstance(applicationContext)