package com.jokopriyono.dicodingfootball.feature.favorite.team

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jokopriyono.dicodingfootball.adapter.TeamAdapter
import com.jokopriyono.dicodingfootball.api.model.TeamResponse
import com.jokopriyono.dicodingfootball.feature.favorite.FavoriteActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class TeamFragment : Fragment(), AnkoComponent<Context> {
    private lateinit var recycler: RecyclerView
    private var teamResponse: TeamResponse? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = createView(AnkoContext.create(requireContext()))

        teamResponse?.let {
            if (it.teams.isNotEmpty()) {
                context?.let { cntx ->
                    recycler.layoutManager = LinearLayoutManager(cntx)
                    val adapter = TeamAdapter(cntx, it.teams)
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
            teamResponse = it.getParcelable(FavoriteActivity.BUNDLE_TEAM)
        }
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = ViewGroup.LayoutParams.MATCH_PARENT, height = ViewGroup.LayoutParams.MATCH_PARENT)
            padding = dip(16)

            recycler = recyclerView {
                lparams(width = ViewGroup.LayoutParams.MATCH_PARENT, height = ViewGroup.LayoutParams.MATCH_PARENT)
            }
        }
    }
}