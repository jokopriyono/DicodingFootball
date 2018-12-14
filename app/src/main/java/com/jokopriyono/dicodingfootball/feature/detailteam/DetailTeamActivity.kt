package com.jokopriyono.dicodingfootball.feature.detailteam

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.R
import com.jokopriyono.dicodingfootball.api.ApiRepository
import com.jokopriyono.dicodingfootball.api.response.Team
import com.jokopriyono.dicodingfootball.feature.detailteam.overview.OverviewFragment
import com.jokopriyono.dicodingfootball.feature.detailteam.players.PlayersFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_team.*

class DetailTeamActivity : AppCompatActivity(), DetailTeamView {
    companion object {
        const val INTENT_DATA = "data"
    }
    private lateinit var presenter: DetailTeamPresenter
    private lateinit var teams: Team

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        teams = intent.getParcelableExtra(INTENT_DATA) as Team

        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = DetailTeamPresenter(this, apiRepository, gson)

        setupViewPager()
        tab_layout.setupWithViewPager(view_pager)

        Picasso.get().load(teams.teamBadge).into(img_team)
        txt_team_year.text = teams.year
        txt_team_name.text = teams.teamName
        txt_team_stadion.text = teams.strStadium

        toolbar.setNavigationOnClickListener { finish() }
    }

    private fun setupViewPager() {
        val overview = OverviewFragment()
        val players = PlayersFragment()
        val adapter = ViewPagerAdapter(supportFragmentManager)
        val bundle = Bundle()
        bundle.putParcelable(INTENT_DATA, teams)
        overview.arguments = bundle

        adapter.addTab(overview, "Overview")
        adapter.addTab(players, "Players")
        view_pager.adapter = adapter
    }

    class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        private var fragments: MutableList<Fragment> = mutableListOf()
        private var titles: MutableList<String> = mutableListOf()

        fun addTab(fragment: Fragment, title: String) {
            fragments.add(fragment)
            titles.add(title)
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position]
        }
    }
}
