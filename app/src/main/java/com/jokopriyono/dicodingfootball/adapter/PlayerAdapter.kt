package com.jokopriyono.dicodingfootball.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.jokopriyono.dicodingfootball.R
import com.jokopriyono.dicodingfootball.api.response.Players
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class PlayerAdapter(private val context: Context, private val players: List<Players>) : RecyclerView.Adapter<PlayerHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder {
        return PlayerHolder(PlayerUI().createView(AnkoContext.create(parent.context, parent)), context)
    }

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        holder.bindItem(players[position])
    }

    override fun getItemCount(): Int = players.size
}

class PlayerHolder(view: View, private val context: Context) : RecyclerView.ViewHolder(view) {
    private val imgPlayer: ImageView = view.find(R.id.player_face)
    private val txtPlayer: TextView = view.find(R.id.player_name)
    private val cardView: CardView = view.find(R.id.cardview)

    fun bindItem(players: Players) {
        players.strCutout?.let {
            Picasso.get().load(it).into(imgPlayer)
        }
        txtPlayer.text = players.strPlayer
        /*cardView.setOnClickListener {
            context.startActivity<DetailTeamActivity>(DetailTeamActivity.INTENT_DATA_TEAM to players)
        }*/
    }

}

class PlayerUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            cardView {
                id = R.id.cardview
                lparams(width = matchParent, height = wrapContent) {
                    margin = dip(8)
                }
                radius = 6f
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    imageView {
                        id = R.id.player_face
                    }.lparams {
                        height = dip(50)
                        width = dip(50)
                    }

                    textView {
                        id = R.id.player_name
                        textSize = 16f
                    }.lparams { margin = dip(16) }
                }
            }

        }
    }

}