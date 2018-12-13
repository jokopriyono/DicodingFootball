package com.jokopriyono.dicodingfootball.feature.main

import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.api.ApiRepository
import com.jokopriyono.dicodingfootball.api.TheSportDBApi
import com.jokopriyono.dicodingfootball.api.model.AllLeagueResponse
import com.jokopriyono.dicodingfootball.api.response.AllLeague
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

import org.mockito.MockitoAnnotations

class MainPresenterTest {
    @Mock
    private lateinit var view: MainView
    @Mock
    private lateinit var gson: Gson
    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: MainPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(view, apiRepository, gson)
    }

    @Test
    fun getAllLeague() {
        val leagues: MutableList<AllLeague> = mutableListOf()
        val response = AllLeagueResponse(leagues)

        GlobalScope.launch {
            Mockito.`when`(gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getAllLeague()).await(),
                    AllLeagueResponse::class.java
            )).thenReturn(response)

            Mockito.verify(view).showSpinner(response.leagues)
        }
    }

    @Test
    fun getLastLeague() {
    }

    @Test
    fun getNextLeague() {
    }
}