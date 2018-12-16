package com.jokopriyono.dicodingfootball.feature.searchmatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.R
import com.jokopriyono.dicodingfootball.api.ApiRepository

class SearchMatchActivity : AppCompatActivity(), SearchMatchView {
    lateinit var presenter: SearchMatchPresenter
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)

        val repository = ApiRepository()
        val gson = Gson()
        presenter = SearchMatchPresenter(this, repository, gson)
    }
}
