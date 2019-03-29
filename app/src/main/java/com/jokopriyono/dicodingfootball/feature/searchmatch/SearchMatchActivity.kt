package com.jokopriyono.dicodingfootball.feature.searchmatch

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.R
import com.jokopriyono.dicodingfootball.adapter.LastLeagueAdapter
import com.jokopriyono.dicodingfootball.api.ApiRepository
import com.jokopriyono.dicodingfootball.api.response.LastLeague
import kotlinx.android.synthetic.main.activity_search_match.*

class SearchMatchActivity : AppCompatActivity(), SearchMatchView, SearchView.OnQueryTextListener {
    companion object {
        private const val TYPE = "Soccer"
    }

    private lateinit var presenter: SearchMatchPresenter
    private var handler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)
        recycler_match.layoutManager = LinearLayoutManager(this)

        val repository = ApiRepository()
        val gson = Gson()
        presenter = SearchMatchPresenter(this, repository, gson)

        search_view.setOnQueryTextListener(this)
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun showData(events: List<LastLeague>?) {
        runOnUiThread {
            loading.visibility = View.GONE
            events?.let {
                val soccer: MutableList<LastLeague> = mutableListOf()
                for (event: LastLeague in it) {
                    if (event.strSport == TYPE) soccer.add(event)
                }

                val adapter = LastLeagueAdapter(applicationContext, soccer)
                recycler_match.adapter = adapter
                recycler_match.visibility = View.VISIBLE
            }
        }
    }

    override fun onQueryTextChange(query: String?): Boolean {
        handler.removeCallbacksAndMessages(null)
        handler.postDelayed({
            query?.let {
                if (query.isNotEmpty()) {
                    runOnUiThread {
                        loading.visibility = View.VISIBLE
                        recycler_match.visibility = View.GONE
                        presenter.searchMatch(query)
                    }
                }
            }
        }, 1000)
        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        handler.removeCallbacksAndMessages(null)
        presenter.searchMatch(query)
        return true
    }
}
