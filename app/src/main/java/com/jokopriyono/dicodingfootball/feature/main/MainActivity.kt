package com.jokopriyono.dicodingfootball.feature.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.R
import com.jokopriyono.dicodingfootball.adapter.LastLeagueAdapter
import com.jokopriyono.dicodingfootball.api.ApiRepository
import com.jokopriyono.dicodingfootball.api.response.AllLeague
import com.jokopriyono.dicodingfootball.api.response.LastLeague
import com.jokopriyono.dicodingfootball.feature.favorite.FavoriteActivity
import com.jokopriyono.dicodingfootball.feature.searchmatch.SearchMatchActivity
import com.jokopriyono.dicodingfootball.feature.team.ListTeamActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), MainView {
    companion object {
        private const val TYPE = "Soccer"
    }

    private lateinit var lastLeague: LastLeagueAdapter
    private var position: Int = 0
    private var idLeagues: MutableList<Int> = mutableListOf()
    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        position = 0
        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)
        presenter.getAllLeague()

        recycler.layoutManager = LinearLayoutManager(applicationContext)
        spinner_league.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View, pos: Int, id: Long) {
                if (position == 0)
                    presenter.getLastLeague(idLeagues[spinner_league.selectedItemPosition])
                else
                    presenter.getNextLeague(idLeagues[spinner_league.selectedItemPosition])
            }
        }
        toolbar.setNavigationOnClickListener { finish() }
        toolbar.inflateMenu(R.menu.menu_search)
        toolbar.setOnMenuItemClickListener {
            startActivity<SearchMatchActivity>()
            return@setOnMenuItemClickListener true
        }
        bottom_navigation.setOnNavigationItemSelectedListener(listener)
    }

    private val listener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.menu_last -> {
                if (position != 0) {
                    recycler.adapter = null
                    spinner_league.adapter = null
                    presenter.getAllLeague()
                }
                position = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_next -> {
                if (position != 1) {
                    recycler.adapter = null
                    spinner_league.adapter = null
                    presenter.getAllLeague()
                }
                position = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_team -> {
                if (position != 2) {
                    startActivity<ListTeamActivity>()
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_fav -> {
                if (position != 3) {
                    startActivity<FavoriteActivity>()
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun showLastLeague(events: List<LastLeague>?) {
        runOnUiThread {
            if (events != null) {
                lastLeague = LastLeagueAdapter(this, events)
                hideLoading()
                recycler.adapter = lastLeague
            } else {
                toast("Data not found for this league")
            }
        }
    }

    override fun showSpinner(allLeague: List<AllLeague>?) {
        hideLoading()
        allLeague?.let {
            val spinnerItems = ArrayList<String>()
            for (i: AllLeague in allLeague) {
                if (i.sportName == TYPE) {
                    spinnerItems.add(i.leagueName)
                    idLeagues.add(i.idLeague.toInt())
                }
            }
            val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
            spinner_league.adapter = spinnerAdapter
        }
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.INVISIBLE
    }
}
