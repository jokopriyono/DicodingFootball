package com.jokopriyono.dicodingfootball.adapter

import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.jokopriyono.dicodingfootball.R
import com.jokopriyono.dicodingfootball.api.response.LastLeague
import com.jokopriyono.dicodingfootball.feature.detail.DetailActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class LastLeagueAdapter(val context: Context, private val events: List<LastLeague>) :
        RecyclerView.Adapter<LastLeagueHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastLeagueHolder {
        return LastLeagueHolder(LastLeagueUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: LastLeagueHolder, position: Int) {
        holder.bindItem(context, events[position])
    }

    override fun getItemCount(): Int = events.size
}

class LastLeagueHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val awayName: TextView = view.find(R.id.txt_away_name)
    private val awayScore: TextView = view.find(R.id.txt_away_score)
    private val homeName: TextView = view.find(R.id.txt_home_name)
    private val homeScore: TextView = view.find(R.id.txt_home_score)
    private val dateEvent: TextView = view.find(R.id.txt_league_date)
    private val cardLastLeague: CardView = view.find(R.id.card_last_league)

    fun bindItem(context: Context, events: LastLeague) {
        awayName.text = events.awayTeamName
        awayScore.text = events.awayScore
        homeName.text = events.homeTeamName
        homeScore.text = events.homeScore
        dateEvent.text = events.dateEvent
        cardLastLeague.setOnClickListener {
            context.startActivity<DetailActivity>("data" to events)
        }
    }

}

class LastLeagueUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            cardView {
                id = R.id.card_last_league
                lparams(width = matchParent, height = wrapContent) {
                    margin = dip(8)
                }
                radius = 6f
                verticalLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)

                    textView {
                        id = R.id.txt_league_date
                        textColor = ContextCompat.getColor(context, R.color.colorAccent)
                        textSize = 11f
                        gravity = Gravity.CENTER_HORIZONTAL
                    }.lparams(width = matchParent, height = wrapContent)

                    linearLayout {
                        gravity = Gravity.CENTER
                        orientation = LinearLayout.HORIZONTAL
                        verticalLayout {
                            gravity = Gravity.CENTER
                            textView {
                                id = R.id.txt_home_name
                                textColor = ContextCompat.getColor(context, R.color.orangeSoft)
                            }.lparams(width = wrapContent, height = wrapContent)
                            textView {
                                id = R.id.txt_home_score
                                typeface = Typeface.DEFAULT_BOLD
                                textSize = 30f
                            }.lparams(width = wrapContent, height = wrapContent)
                        }.lparams(weight = 1f, width = dip(0), height = wrapContent)
                        textView("VS") {

                        }.lparams(width = wrapContent, height = wrapContent)
                        verticalLayout {
                            gravity = Gravity.CENTER
                            textView {
                                id = R.id.txt_away_name
                                textColor = ContextCompat.getColor(context, R.color.orangeSoft)
                            }.lparams(width = wrapContent, height = wrapContent)
                            textView {
                                id = R.id.txt_away_score
                                typeface = Typeface.DEFAULT_BOLD
                                textSize = 30f
                            }.lparams(width = wrapContent, height = wrapContent)
                        }.lparams(weight = 1f, width = dip(0), height = wrapContent)
                    }.lparams(width = matchParent, height = wrapContent)
                }
            }

        }
    }

}