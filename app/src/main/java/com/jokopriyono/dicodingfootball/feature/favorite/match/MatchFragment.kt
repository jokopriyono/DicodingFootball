package com.jokopriyono.dicodingfootball.feature.favorite.match

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jokopriyono.dicodingfootball.R
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
            it.events?.let { lastLeague ->
                if (lastLeague.isNotEmpty()) {
                    context?.let { cntx ->
                        recycler.layoutManager = LinearLayoutManager(cntx)
                        val adapter = LastLeagueAdapter(cntx, it.events)
                        recycler.adapter = adapter
                    }
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
                id = R.id.recycler_match
                lparams(width = ViewGroup.LayoutParams.MATCH_PARENT, height = ViewGroup.LayoutParams.MATCH_PARENT)
            }
        }
    }
}