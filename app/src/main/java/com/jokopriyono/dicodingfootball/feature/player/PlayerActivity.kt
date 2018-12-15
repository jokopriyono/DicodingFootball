package com.jokopriyono.dicodingfootball.feature.player

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jokopriyono.dicodingfootball.R
import com.jokopriyono.dicodingfootball.api.response.Players
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity(), PlayerView {
    companion object {
        const val INTENT_DATA = "data"
    }

    lateinit var presenter: PlayerPresenter
    lateinit var players: Players

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        players = intent.getParcelableExtra(INTENT_DATA)

        presenter = PlayerPresenter()

        toolbar.title = players.strPlayer
        txt_desc.text = players.strDescriptionEN
        txt_position.text = players.strPosition
        txt_weight.text = players.strWeight
        txt_height.text = players.strHeight
        players.strFanart1?.let {
            Picasso.get().load(it).placeholder(R.mipmap.ic_launcher).into(img_player)
        }.run {
            players.strThumb?.let {
                Picasso.get().load(it).placeholder(R.mipmap.ic_launcher).into(img_player)
            }
        }

        toolbar.setNavigationOnClickListener { finish() }
    }
}
