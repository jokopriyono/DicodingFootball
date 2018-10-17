package com.jokopriyono.dicodingfootball.feature.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.R
import com.jokopriyono.dicodingfootball.api.ApiRepository
import com.jokopriyono.dicodingfootball.api.response.LastLeague
import com.jokopriyono.dicodingfootball.api.response.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailView {
    private lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val data: LastLeague = intent.getParcelableExtra("data")

        txt_league_date.text = data.dateEvent
        txt_home_name.text = data.homeTeamName
        txt_away_name.text = data.awayTeamName

        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailPresenter(this, request, gson)
        presenter.getTwoTeams(data.homeTeamName, data.awayTeamName)
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.INVISIBLE
    }

    override fun showTeams(dataHome: Team?, dataAway: Team?) {
        Log.d("pesan", ""+dataHome?.urlTeamLogo)
        Log.d("pesan", ""+dataAway?.urlTeamLogo)
        Picasso.get().load(dataHome?.urlTeamLogo).into(img_home_team)
        Picasso.get().load(dataAway?.urlTeamLogo).into(img_away_team)
    }
}
