package com.jokopriyono.dicodingfootball.feature.detailteam.overview

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.TextView
import com.jokopriyono.dicodingfootball.api.response.Team
import com.jokopriyono.dicodingfootball.feature.detailteam.DetailTeamActivity
import org.jetbrains.anko.*

class OverviewFragment : Fragment(), AnkoComponent<Context> {
    private var team: Team? = null
    private lateinit var txtOverview: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = createView(AnkoContext.create(requireContext()))

        team?.let {
            txtOverview.text = it.strDescriptionEN
        }

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle: Bundle? = this.arguments
        bundle?.let {
            team = it.getParcelable(DetailTeamActivity.INTENT_DATA)
        }
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = MATCH_PARENT, height = MATCH_PARENT)
            padding = dip(16)

            scrollView {
                lparams(width = MATCH_PARENT, height = MATCH_PARENT)
                verticalLayout {
                    lparams(width = MATCH_PARENT, height = MATCH_PARENT)

                    txtOverview = textView("Overview")
                }
            }
        }
    }
}