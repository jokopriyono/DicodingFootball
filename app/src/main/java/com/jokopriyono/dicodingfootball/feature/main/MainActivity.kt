package com.jokopriyono.dicodingfootball.feature.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.R
import com.jokopriyono.dicodingfootball.adapter.FootballAdapter
import com.jokopriyono.dicodingfootball.adapter.LastLeagueAdapter
import com.jokopriyono.dicodingfootball.api.ApiRepository
import com.jokopriyono.dicodingfootball.api.response.AllLeague
import com.jokopriyono.dicodingfootball.api.response.LastLeague
import com.jokopriyono.dicodingfootball.api.response.Team
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var lastLeague: LastLeagueAdapter

    override fun showLastLeague(events: List<LastLeague>) {
        lastLeague = LastLeagueAdapter(this, events)
        recycler.adapter = lastLeague
    }

    private var idLeagues: MutableList<Int> = mutableListOf()

    override fun showSpinner(allLeague: List<AllLeague>) {
        val spinnerItems = ArrayList<String>()
        for (i: AllLeague in allLeague) {
            if (i.sportName.equals("Soccer")) {
                spinnerItems.add(i.leagueName)
                idLeagues.add(i.idLeague.toInt())
            }
        }
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner_league.adapter = spinnerAdapter
    }

    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: FootballAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)
        presenter.getAllLeague()
        /*
        spinner_league.adapter = spinnerAdapter

        adapter = FootballAdapter(this, teams)
        recycler.adapter = adapter


        swipeRefresh.onRefresh {
            presenter.getTeams(spinner.selectedItem.toString())
        }*/

        recycler.layoutManager = LinearLayoutManager(this)
        spinner_league.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                presenter.getLastTeam(idLeagues.get(spinner_league.selectedItemPosition))
            }
        }
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.INVISIBLE
    }

    override fun showTeams(data: List<Team>) {
//        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
