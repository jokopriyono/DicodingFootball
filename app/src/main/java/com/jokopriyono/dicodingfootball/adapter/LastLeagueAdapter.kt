package com.jokopriyono.dicodingfootball.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.jokopriyono.dicodingfootball.R
import com.jokopriyono.dicodingfootball.api.response.LastLeague
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class LastLeagueAdapter(val context: Context, private val events: List<LastLeague>):
        RecyclerView.Adapter<LastLeagueHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastLeagueHolder {
        return LastLeagueHolder(LastLeagueUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: LastLeagueHolder, position: Int) {
        holder.bindItem(events[position])
    }

    override fun getItemCount(): Int = events.size
}

class LastLeagueHolder(view: View) : RecyclerView.ViewHolder(view){
    private val teamBadge: ImageView = view.find(R.id.team_badge)
    private val awayName: TextView = view.find(R.id.away_name)

    fun bindItem(events: LastLeague) {
        awayName.text = events.awayTeamName
    }

}

class LastLeagueUI : AnkoComponent<ViewGroup>{
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){
            cardView {
                lparams(width = matchParent, height = wrapContent){
                    margin = dip(8)
                }
                radius = 6f
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    imageView {
                        id = R.id.team_badge
                    }.lparams{
                        height = dip(50)
                        width = dip(50)
                    }

                    textView {
                        id = R.id.away_name
                        textSize = 16f
                    }.lparams{ margin = dip(16) }
                }
            }

        }
    }

}