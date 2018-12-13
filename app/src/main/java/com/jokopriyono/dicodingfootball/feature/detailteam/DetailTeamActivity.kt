package com.jokopriyono.dicodingfootball.feature.detailteam

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.R
import com.jokopriyono.dicodingfootball.api.ApiRepository
import com.jokopriyono.dicodingfootball.feature.detailteam.overview.OverviewFragment
import com.jokopriyono.dicodingfootball.feature.detailteam.players.PlayersFragment
import kotlinx.android.synthetic.main.activity_detail_team.*

class DetailTeamActivity : AppCompatActivity(), DetailTeamView {
    private lateinit var presenter: DetailTeamPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = DetailTeamPresenter(this, apiRepository, gson)

        setupViewPager()
        tab_layout.setupWithViewPager(view_pager)
        toolbar.setNavigationOnClickListener { finish() }
    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addTab(OverviewFragment(), "Overview")
        adapter.addTab(PlayersFragment(), "Players")
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
