package com.jokopriyono.dicodingfootball.feature.favorite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jokopriyono.dicodingfootball.R

class FavoriteActivity : AppCompatActivity(), FavoriteView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
    }

    override fun showAllFavorite() {

    }
}
