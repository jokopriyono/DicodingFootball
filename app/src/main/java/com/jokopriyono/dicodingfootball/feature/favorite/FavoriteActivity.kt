package com.jokopriyono.dicodingfootball.feature.favorite

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jokopriyono.dicodingfootball.R
import com.jokopriyono.dicodingfootball.adapter.ViewPagerAdapter
import com.jokopriyono.dicodingfootball.api.model.LastLeagueResponse
import com.jokopriyono.dicodingfootball.api.model.TeamResponse
import com.jokopriyono.dicodingfootball.api.response.LastLeague
import com.jokopriyono.dicodingfootball.api.response.Team
import com.jokopriyono.dicodingfootball.feature.favorite.match.MatchFragment
import com.jokopriyono.dicodingfootball.feature.favorite.team.TeamFragment
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity(), FavoriteView {
    companion object {
        const val BUNDLE_LAST = "lastleague"
        const val BUNDLE_TEAM = "teams"
    }

    lateinit var presenter: FavoritePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        presenter = FavoritePresenter(this)
        presenter.loadData(applicationContext)

        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun showAllFavorite(lastLeagues: MutableList<LastLeague>, teams: MutableList<Team>) {
        val matchFragment = MatchFragment()
        val teamFragment = TeamFragment()
        val lastResponse = LastLeagueResponse(lastLeagues)
        val teamResponse = TeamResponse(teams)
        val bundle = Bundle()
        bundle.putParcelable(BUNDLE_LAST, lastResponse)
        bundle.putParcelable(BUNDLE_TEAM, teamResponse)
        matchFragment.arguments = bundle
        teamFragment.arguments = bundle

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addTab(matchFragment, "Match")
        adapter.addTab(teamFragment, "Team")

        runOnUiThread {
            view_pager.adapter = adapter
            tab_layout.setupWithViewPager(view_pager)
        }
    }
}
