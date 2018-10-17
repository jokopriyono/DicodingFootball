package com.jokopriyono.dicodingfootball.feature.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.ApiRepository
import com.jokopriyono.dicodingfootball.adapter.FootballAdapter
import com.jokopriyono.dicodingfootball.R
import com.jokopriyono.dicodingfootball.Team
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MainActivity : AppCompatActivity(), MainView{
    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: FootballAdapter
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linearLayout {
            lparams(matchParent, matchParent)
            orientation = LinearLayout.VERTICAL
            padding = dip(16)
            spinner = spinner()
            /*swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light
                )
            }*/
            relativeLayout {
                listTeam = recyclerView {
                    layoutManager = LinearLayoutManager(context)
                    overScrollMode = View.OVER_SCROLL_NEVER
                }.lparams(width = matchParent, height = matchParent)

                progressBar = progressBar {
                }.lparams {
                    centerHorizontally()
                }
            }.lparams(width = matchParent, height = wrapContent)
        }

        val spinnerItems = resources.getStringArray(R.array.spinner)
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        adapter = FootballAdapter(this, teams)
        listTeam.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?){ }

            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                presenter.getTeams(spinner.selectedItem.toString())
            }
        }

        /*swipeRefresh.onRefresh {
            presenter.getTeams(spinner.selectedItem.toString())
        }*/
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showTeams(data: List<Team>) {
//        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
