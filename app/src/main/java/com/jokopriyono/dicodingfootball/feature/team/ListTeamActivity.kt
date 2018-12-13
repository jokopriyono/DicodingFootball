package com.jokopriyono.dicodingfootball.feature.team

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.R
import com.jokopriyono.dicodingfootball.adapter.TeamAdapter
import com.jokopriyono.dicodingfootball.api.ApiRepository
import com.jokopriyono.dicodingfootball.api.response.AllLeague
import com.jokopriyono.dicodingfootball.api.response.Team
import kotlinx.android.synthetic.main.activity_list_team.*

class ListTeamActivity : AppCompatActivity(), ListTeamView, AdapterView.OnItemSelectedListener {
    private lateinit var presenter: ListTeamPresenter
    private var idLeagues: MutableList<Int> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_team)
        recycler_team.layoutManager = LinearLayoutManager(this)

        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = ListTeamPresenter(this, apiRepository, gson)
        presenter.getAllLeague()

        spinner_team.onItemSelectedListener = this
    }

    override fun onItemSelected(adapter: AdapterView<*>?, view: View, pos: Int, id: Long) {
        loading.visibility = VISIBLE
        presenter.getTeams(spinner_team.selectedItem.toString())
    }

    override fun onNothingSelected(adapter: AdapterView<*>?) {

    }

    override fun showData(teams: List<Team>) {
        loading.visibility = GONE
        val adapter = TeamAdapter(applicationContext, teams)
        recycler_team.adapter = adapter
        recycler_team.visibility = VISIBLE
    }

    override fun showSpinner(leagues: List<AllLeague>) {
        val spinnerItems = ArrayList<String>()
        for (i: AllLeague in leagues) {
            if (i.sportName == "Soccer") {
                spinnerItems.add(i.leagueName)
                idLeagues.add(i.idLeague.toInt())
            }
        }
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner_team.adapter = spinnerAdapter
    }
}
