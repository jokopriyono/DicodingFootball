package com.jokopriyono.dicodingfootball.feature.favorite.match

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jokopriyono.dicodingfootball.adapter.LastLeagueAdapter
import com.jokopriyono.dicodingfootball.api.model.LastLeagueResponse
import com.jokopriyono.dicodingfootball.feature.favorite.FavoriteActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MatchFragment : Fragment(), AnkoComponent<Context> {
    private lateinit var recycler: RecyclerView
    private var lastLeagueResponse: LastLeagueResponse? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = createView(AnkoContext.create(requireContext()))

        lastLeagueResponse?.let {
            if (it.events.isNotEmpty()) {
                context?.let { cntx ->
                    recycler.layoutManager = LinearLayoutManager(cntx)
                    val adapter = LastLeagueAdapter(cntx, it.events)
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
            lastLeagueResponse = it.getParcelable(FavoriteActivity.BUNDLE_LAST)
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