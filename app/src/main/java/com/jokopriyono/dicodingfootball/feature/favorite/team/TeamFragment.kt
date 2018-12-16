package com.jokopriyono.dicodingfootball.feature.favorite.team

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class TeamFragment : Fragment(), AnkoComponent<Context> {
    private lateinit var recycler: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
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