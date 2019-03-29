package com.jokopriyono.dicodingfootball.feature.detail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.R
import com.jokopriyono.dicodingfootball.R.menu.menu_star_unactive
import com.jokopriyono.dicodingfootball.api.ApiRepository
import com.jokopriyono.dicodingfootball.api.response.LastLeague
import com.jokopriyono.dicodingfootball.api.response.Team
import com.jokopriyono.dicodingfootball.data.FavoriteMatch
import com.jokopriyono.dicodingfootball.data.database
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailActivity : AppCompatActivity(), DetailView, Toolbar.OnMenuItemClickListener {

    companion object {
        const val INTENT_DATA: String = "data"
    }

    private lateinit var lastLeague: LastLeague
    private lateinit var presenter: DetailPresenter

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        lastLeague = intent.getParcelableExtra(INTENT_DATA)

        txt_league_date.text = lastLeague.dateEvent
        txt_home_name.text = lastLeague.homeTeamName
        txt_away_name.text = lastLeague.awayTeamName
        txt_home_score.text = lastLeague.homeScore
        txt_away_score.text = lastLeague.awayScore
        txt_yellow_card_home.text = lastLeague.homeYellowCards?.replace(";", "\n")
        txt_red_card_home.text = lastLeague.homeRedCards?.replace(";", "\n")
        txt_yellow_card_away.text = lastLeague.awayYellowCards?.replace(";", "\n")
        txt_red_card_away.text = lastLeague.awayRedCards?.replace(";", "\n")
        txt_home_goal.text = lastLeague.homeGoalDetails?.replace(";", "\n")
        txt_away_goal.text = lastLeague.awayGoalDetails?.replace(";", "\n")
        txt_home_id.text = lastLeague.idHomeTeam
        txt_away_id.text = lastLeague.idAwayTeam
        toolbar_detail.title = lastLeague.leagueName

        lastLeague.idEvent?.let {
            favoriteState(it)
        }
        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailPresenter(this, request, gson)
        presenter.getTwoTeams(lastLeague.homeTeamName, lastLeague.awayTeamName)

        toolbar_detail.setNavigationOnClickListener { finish() }
        toolbar_detail.inflateMenu(menu_star_unactive)
        menuItem = toolbar_detail.menu
        toolbar_detail.setOnMenuItemClickListener(this)
        setFavorite()
    }

    override fun onMenuItemClick(menu: MenuItem): Boolean {
        return when (menu.itemId) {
            R.id.menu_favorite -> {
                lastLeague.idEvent?.let {
                    if (isFavorite) removeFromFavorite(it) else addToFavorite()

                    isFavorite = !isFavorite
                    setFavorite()
                }

                true
            }

            else -> true
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.v_star_active)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.v_star_unactive)
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(FavoriteMatch.TABLE_FAV_MATCH,
                        FavoriteMatch.idEvent to lastLeague.idEvent,
                        FavoriteMatch.strEvent to lastLeague.eventName,
                        FavoriteMatch.strFilename to lastLeague.eventFilename,
                        FavoriteMatch.strLeague to lastLeague.leagueName,
                        FavoriteMatch.strHomeTeam to lastLeague.homeTeamName,
                        FavoriteMatch.strAwayTeam to lastLeague.awayTeamName,
                        FavoriteMatch.intHomeScore to lastLeague.homeScore,
                        FavoriteMatch.intAwayScore to lastLeague.awayScore,
                        FavoriteMatch.strHomeGoalDetails to lastLeague.homeGoalDetails,
                        FavoriteMatch.strAwayGoalDetails to lastLeague.awayGoalDetails,
                        FavoriteMatch.strHomeRedCards to lastLeague.homeRedCards,
                        FavoriteMatch.strAwayRedCards to lastLeague.awayRedCards,
                        FavoriteMatch.strHomeYellowCards to lastLeague.homeYellowCards,
                        FavoriteMatch.strAwayYellowCards to lastLeague.awayYellowCards,
                        FavoriteMatch.dateEvent to lastLeague.dateEvent,
                        FavoriteMatch.strDate to lastLeague.strDate,
                        FavoriteMatch.idHomeTeam to lastLeague.idHomeTeam,
                        FavoriteMatch.idAwayTeam to lastLeague.idAwayTeam)
            }
            toast(getString(R.string.added_fav))
        } catch (e: Exception) {
            toast(e.localizedMessage)
        }
    }

    private fun favoriteState(idEvent: String) {
        database.use {
            val result = select(FavoriteMatch.TABLE_FAV_MATCH)
                    .whereArgs("(idEvent = {id})", "id" to idEvent)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun removeFromFavorite(idEvent: String) {
        try {
            database.use {
                delete(FavoriteMatch.TABLE_FAV_MATCH, "(idEvent = ${idEvent.toLong()})", null)
            }
            toast(getString(R.string.removed_fav))
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.INVISIBLE
    }

    override fun showTeams(dataHome: Team?, dataAway: Team?) {
        Picasso.get().load(dataHome?.teamBadge).into(img_home_team)
        Picasso.get().load(dataAway?.teamBadge).into(img_away_team)
    }
}
