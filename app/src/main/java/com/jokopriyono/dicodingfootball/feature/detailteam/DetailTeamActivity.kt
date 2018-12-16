package com.jokopriyono.dicodingfootball.feature.detailteam

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View.GONE
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

class DetailTeamActivity : AppCompatActivity(), DetailTeamView {
    companion object {
        const val INTENT_DATA_TEAM = "data"
        const val INTENT_DATA_PLAYERS = "players"
    }

    private lateinit var presenter: DetailTeamPresenter
    private lateinit var teams: Team

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = DetailTeamPresenter(this, apiRepository, gson)

        teams = intent.getParcelableExtra(INTENT_DATA_TEAM) as Team
        teams.teamName?.let {
            presenter.getPlayers(it)
        }

        Picasso.get().load(teams.teamBadge).into(img_team)
        txt_team_year.text = teams.year
        txt_team_name.text = teams.teamName
        txt_team_stadion.text = teams.strStadium

        toolbar.setNavigationOnClickListener { finish() }
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
