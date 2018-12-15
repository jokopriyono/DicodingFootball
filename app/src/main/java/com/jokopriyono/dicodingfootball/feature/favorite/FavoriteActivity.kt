package com.jokopriyono.dicodingfootball.feature.favorite

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.jokopriyono.dicodingfootball.R
import com.jokopriyono.dicodingfootball.adapter.FavoriteAdapter
import com.jokopriyono.dicodingfootball.api.response.LastLeague
import com.jokopriyono.dicodingfootball.data.FavoriteMatch
import com.jokopriyono.dicodingfootball.data.database
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.themedToolbar
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavoriteActivity : AppCompatActivity(), FavoriteView {
    private var lastLeagues: MutableList<LastLeague> = mutableListOf()
    private lateinit var adapter: FavoriteAdapter
    private lateinit var listEvent: RecyclerView
    lateinit var swipeRefresh: SwipeRefreshLayout
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
            toolbar = themedToolbar {
                lparams(width = matchParent, height = wrapContent)
                navigationIcon = ContextCompat.getDrawable(applicationContext, R.drawable.v_left)
                backgroundColor = ContextCompat.getColor(applicationContext, R.color.colorPrimary)
            }
            linearLayout {
                lparams(width = matchParent, height = matchParent)
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)

                swipeRefresh = swipeRefreshLayout {
                    setColorSchemeResources(R.color.colorAccent,
                            android.R.color.holo_green_light,
                            android.R.color.holo_orange_light,
                            android.R.color.holo_red_light)

                    listEvent = recyclerView {
                        id = R.id.recycler
                        lparams(width = matchParent, height = matchParent)
                        layoutManager = LinearLayoutManager(context)
                    }
                }

            }
        }
        toolbar.title = getString(R.string.favorites)
        toolbar.setTitleTextColor(Color.WHITE)

        adapter = FavoriteAdapter(this, lastLeagues)
        listEvent.adapter = adapter

        showAllFavorite()
        swipeRefresh.onRefresh {
            lastLeagues.clear()
            showAllFavorite()
        }

        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun showAllFavorite() {
        try {
            database.use {
                swipeRefresh.isRefreshing = false
                val result = select(FavoriteMatch.TABLE_FAV_MATCH)
                val match = result.parseList(classParser<FavoriteMatch>())
                for (data: FavoriteMatch in match) {
                    val lastLeague = LastLeague(
                            data.id.toString(),
                            data.idEvent,
                            data.strEvent,
                            data.strFilename,
                            data.strLeague,
                            data.strHomeTeam,
                            data.strAwayTeam,
                            data.intHomeScore,
                            data.intAwayScore,
                            data.strHomeGoalDetails,
                            data.strHomeYellowCards,
                            data.strHomeRedCards,
                            data.strAwayGoalDetails,
                            data.strAwayYellowCards,
                            data.strAwayRedCards,
                            data.dateEvent,
                            data.strDate,
                            data.idHomeTeam,
                            data.idAwayTeam)
                    lastLeagues.add(lastLeague)
                }
                adapter.notifyDataSetChanged()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "" + e.localizedMessage, Toast.LENGTH_SHORT).show()
        }

    }
}
