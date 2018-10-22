package com.jokopriyono.dicodingfootball.feature.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.R
import com.jokopriyono.dicodingfootball.api.ApiRepository
import com.jokopriyono.dicodingfootball.api.response.LastLeague
import com.jokopriyono.dicodingfootball.api.response.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailView {
    companion object {
        val INTENT_DATA: String = "data"
    }
    private lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val data: LastLeague = intent.getParcelableExtra(INTENT_DATA)

        txt_league_date.text = data.dateEvent
        txt_home_name.text = data.homeTeamName
        txt_away_name.text = data.awayTeamName
        txt_home_score.text = data.homeScore
        txt_away_score.text = data.awayScore
        txt_yellow_card_home.text = data.homeYellowCards?.replace(";", "\n")
        txt_red_card_home.text = data.homeRedCards?.replace(";", "\n")
        txt_yellow_card_away.text = data.awayYellowCards?.replace(";", "\n")
        txt_red_card_away.text = data.awayRedCards?.replace(";", "\n")
        txt_home_goal.text = data.homeGoalDetails?.replace(";", "\n")
        txt_away_goal.text = data.awayGoalDetails?.replace(";", "\n")
        txt_home_id.text = data.idHomeTeam
        txt_away_id.text = data.idAwayTeam
        toolbar_detail.title = data.leagueName

        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailPresenter(this, request, gson)
        presenter.getTwoTeams(data.homeTeamName, data.awayTeamName)

        toolbar_detail.setNavigationOnClickListener { finish() }
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.INVISIBLE
    }

    override fun showTeams(dataHome: Team?, dataAway: Team?) {
        Picasso.get().load(dataHome?.urlTeamLogo).into(img_home_team)
        Picasso.get().load(dataAway?.urlTeamLogo).into(img_away_team)
    }
}
