package com.jokopriyono.dicodingfootball.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.jokopriyono.dicodingfootball.R
import com.jokopriyono.dicodingfootball.api.response.Team
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class FootballAdapter(val context: Context, private val teams: List<Team>):
        RecyclerView.Adapter<FootballHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FootballHolder {
        return FootballHolder(FootballUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: FootballHolder, position: Int) {
        holder.bindItem(teams[position])
    }

    override fun getItemCount(): Int = teams.size
}

class FootballHolder(view: View) : RecyclerView.ViewHolder(view){
    private val teamBadge: ImageView = view.find(R.id.team_badge)
    private val teamName: TextView = view.find(R.id.team_name)

    fun bindItem(teams: Team) {
        Picasso.get().load(teams.teamBadge).into(teamBadge)
        teamName.text = teams.teamName
    }

}

class FootballUI : AnkoComponent<ViewGroup>{
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
                        id = R.id.team_name
                        textSize = 16f
                    }.lparams{ margin = dip(16) }
                }
            }

        }
    }

}