package com.jokopriyono.dicodingfootball.feature.team

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.R
import com.jokopriyono.dicodingfootball.adapter.TeamAdapter
import com.jokopriyono.dicodingfootball.api.ApiRepository
import com.jokopriyono.dicodingfootball.api.response.AllLeague
import com.jokopriyono.dicodingfootball.api.response.Team
import kotlinx.android.synthetic.main.activity_list_team.*

class ListTeamActivity : AppCompatActivity(), ListTeamView,
        AdapterView.OnItemSelectedListener,
        SearchView.OnQueryTextListener, SearchView.OnCloseListener {
    private lateinit var presenter: ListTeamPresenter
    private var idLeagues: MutableList<Int> = mutableListOf()
    private var handler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_team)
        recycler_team.layoutManager = LinearLayoutManager(this)

        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = ListTeamPresenter(this, apiRepository, gson)
        presenter.getAllLeague()

        spinner_team.onItemSelectedListener = this
        search_view.setOnQueryTextListener(this)
        search_view.setOnCloseListener(this)
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun onClose(): Boolean {
        spinner_team.visibility = VISIBLE
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        handler.removeCallbacksAndMessages(null)
        handler.postDelayed({
            query?.let {
                if (query.isNotEmpty()) {
                    runOnUiThread {
                        loading.visibility = VISIBLE
                        recycler_team.visibility = GONE
                        presenter.searchTeams(query)
                    }
                }
            }
        }, 1000)
        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        handler.removeCallbacksAndMessages(null)
        presenter.searchTeams(query)
        return true
    }

    override fun onItemSelected(adapter: AdapterView<*>?, view: View, pos: Int, id: Long) {
        loading.visibility = VISIBLE
        recycler_team.visibility = GONE
        presenter.getTeams(spinner_team.selectedItem.toString())
    }

    override fun onNothingSelected(adapter: AdapterView<*>?) {

    }

    override fun showData(teams: List<Team>) {
        loading.visibility = GONE
        val adapter = TeamAdapter(this, teams)
        recycler_team.adapter = adapter
        recycler_team.visibility = VISIBLE
    }

    override fun showDataHideSpinner(teams: List<Team>) {
        showData(teams)
        spinner_team.visibility = GONE
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
