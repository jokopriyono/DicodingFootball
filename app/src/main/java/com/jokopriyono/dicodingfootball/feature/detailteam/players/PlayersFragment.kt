package com.jokopriyono.dicodingfootball.feature.detailteam.players

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import org.jetbrains.anko.*

class PlayersFragment : Fragment(), AnkoComponent<Context> {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = MATCH_PARENT, height = MATCH_PARENT)
            padding = dip(16)

            scrollView {
                lparams(width = MATCH_PARENT, height = MATCH_PARENT)
                verticalLayout {
                    lparams(width = MATCH_PARENT, height = MATCH_PARENT)

                    textView("Players")
                }
            }
        }
    }
}