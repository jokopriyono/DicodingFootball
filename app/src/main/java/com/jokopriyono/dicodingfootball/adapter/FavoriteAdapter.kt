package com.jokopriyono.dicodingfootball.adapter

import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.jokopriyono.dicodingfootball.R
import com.jokopriyono.dicodingfootball.R.id.*
import com.jokopriyono.dicodingfootball.api.response.LastLeague
import com.jokopriyono.dicodingfootball.feature.detail.DetailActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class FavoriteAdapter(private val context: Context, private val favoriteMatch: MutableList<LastLeague>)
    : RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favoriteMatch[position], context)
    }

    override fun getItemCount(): Int = favoriteMatch.size

}

class TeamUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            cardView {
                id = card_last_league
                lparams(width = matchParent, height = wrapContent) {
                    margin = dip(8)
                }
                radius = 6f
                verticalLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)

                    textView {
                        id = txt_league_date
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
                                id = txt_home_name
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
                                id = txt_away_name
                                textColor = ContextCompat.getColor(context, R.color.orangeSoft)
                            }.lparams(width = wrapContent, height = wrapContent)
                            textView {
                                id = txt_away_score
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

class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val awayName: TextView = view.find(R.id.txt_away_name)
    private val awayScore: TextView = view.find(R.id.txt_away_score)
    private val homeName: TextView = view.find(R.id.txt_home_name)
    private val homeScore: TextView = view.find(R.id.txt_home_score)
    private val dateEvent: TextView = view.find(R.id.txt_league_date)

    fun bindItem(favoriteMatch: LastLeague, context: Context) {
//        Picasso.get().load(favoriteMatch.).into(teamBadge)
        awayName.text = favoriteMatch.awayTeamName
        homeName.text = favoriteMatch.homeTeamName
        awayScore.text = favoriteMatch.awayScore
        homeScore.text = favoriteMatch.homeScore
        dateEvent.text = favoriteMatch.dateEvent
        itemView.setOnClickListener { context.startActivity<DetailActivity>(
                DetailActivity.INTENT_DATA to favoriteMatch) }
    }
}