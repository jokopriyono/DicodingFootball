package com.jokopriyono.dicodingfootball

import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.api.ApiRepository
import com.jokopriyono.dicodingfootball.api.TheSportDBApi
import com.jokopriyono.dicodingfootball.api.model.AllLeagueResponse
import com.jokopriyono.dicodingfootball.api.response.AllLeague
import com.jokopriyono.dicodingfootball.feature.main.MainPresenter
import com.jokopriyono.dicodingfootball.feature.main.MainView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import kotlin.coroutines.CoroutineContext

class MainUnitActivityTest {
    @Mock
    private
    lateinit var view: MainView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: MainPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(view, apiRepository, gson)
    }

    @Test
    fun testGetAllLeague() {
        val leagues: MutableList<AllLeague> = mutableListOf()
        val response = AllLeagueResponse(leagues)

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getAllLeague()).await(),
                    AllLeagueResponse::class.java
            )).thenReturn(response)

            Mockito.verify(view).hideLoading()
            Mockito.verify(view).showSpinner(response.leagues)
        }
    }
}