package com.jokopriyono.dicodingfootball.feature.detailteam

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.R
import com.jokopriyono.dicodingfootball.adapter.ViewPagerAdapter
import com.jokopriyono.dicodingfootball.api.ApiRepository
import com.jokopriyono.dicodingfootball.api.model.PlayersResponse
import com.jokopriyono.dicodingfootball.api.response.Team
import com.jokopriyono.dicodingfootball.feature.detailteam.overview.OverviewFragment
import com.jokopriyono.dicodingfootball.feature.detailteam.players.PlayersFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_team.*

class DetailTeamActivity : AppCompatActivity(), DetailTeamView, Toolbar.OnMenuItemClickListener {
    companion object {
        const val INTENT_DATA_TEAM = "data"
        const val INTENT_DATA_PLAYERS = "players"
    }

    private lateinit var presenter: DetailTeamPresenter
    private lateinit var teams: Team
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = DetailTeamPresenter(this, applicationContext, apiRepository, gson)

        teams = intent.getParcelableExtra(INTENT_DATA_TEAM) as Team
        teams.teamName?.let {
            presenter.getPlayers(it)
        }
        teams.teamId?.let {
            presenter.selectTeamSB(it)
        }

        Picasso.get().load(teams.teamBadge).into(img_team)
        txt_team_year.text = teams.year
        txt_team_name.text = teams.teamName
        txt_team_stadion.text = teams.strStadium

        toolbar.setNavigationOnClickListener { finish() }
        toolbar.inflateMenu(R.menu.menu_star_unactive)
        menuItem = toolbar.menu
        toolbar.setOnMenuItemClickListener(this)
        switchFavorite()
    }

    override fun setFavorite(favorite: Boolean) {
        isFavorite = favorite
    }

    override fun onMenuItemClick(menu: MenuItem): Boolean {
        return when (menu.itemId) {
            R.id.menu_favorite -> {
                teams.teamId?.let {
                    if (isFavorite) presenter.removeTeamDB(it) else presenter.addTeamDB(teams)

                    isFavorite = !isFavorite
                    switchFavorite()
                }

                true
            }

            else -> true
        }
    }

    private fun switchFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.v_star_active)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.v_star_unactive)
    }

    override fun showData(players: PlayersResponse) {
        val overviewFrag = OverviewFragment()
        val playersFrag = PlayersFragment()
        val adapter = ViewPagerAdapter(supportFragmentManager)

        val bundle = Bundle()
        val bundle2 = Bundle()
        bundle.putParcelable(INTENT_DATA_TEAM, teams)
        bundle2.putParcelable(INTENT_DATA_PLAYERS, players)

        overviewFrag.arguments = bundle
        playersFrag.arguments = bundle2
        adapter.addTab(overviewFrag, "Overview")
        adapter.addTab(playersFrag, "Players")

        runOnUiThread {
            loading.visibility = GONE
            view_pager.adapter = adapter
            tab_layout.setupWithViewPager(view_pager)
        }
    }
}
