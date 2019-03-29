package com.jokopriyono.dicodingfootball.feature.detailteam.players

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jokopriyono.dicodingfootball.adapter.PlayerAdapter
import com.jokopriyono.dicodingfootball.api.model.PlayersResponse
import com.jokopriyono.dicodingfootball.feature.detailteam.DetailTeamActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class PlayersFragment : Fragment(), AnkoComponent<Context> {
    private var players: PlayersResponse? = null
    private lateinit var recycler: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = createView(AnkoContext.create(requireContext()))

        players?.let {
            if (it.player.isNotEmpty()) {
                context?.let { cntx ->
                    recycler.layoutManager = LinearLayoutManager(cntx)
                    val adapter = PlayerAdapter(cntx, it.player)
                    recycler.adapter = adapter
                }
            }
        }

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle: Bundle? = this.arguments
        bundle?.let {
            players = it.getParcelable(DetailTeamActivity.INTENT_DATA_PLAYERS)
        }
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = MATCH_PARENT, height = MATCH_PARENT)
            padding = dip(16)

            recycler = recyclerView {
                lparams(width = MATCH_PARENT, height = MATCH_PARENT)
            }
        }
    }
}