package com.jokopriyono.dicodingfootball

import com.google.gson.Gson
import com.jokopriyono.dicodingfootball.api.ApiRepository
import com.jokopriyono.dicodingfootball.api.TheSportDBApi
import com.jokopriyono.dicodingfootball.api.model.AllLeagueResponse
import com.jokopriyono.dicodingfootball.feature.main.MainView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
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
        presenter = MainPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetAllLeague() {
        presenter.getAllLeague()
        /*val leagues: MutableList<AllLeague> = mutableListOf()
        val response = AllLeagueResponse(leagues)

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getAllLeague()).await(),
                    AllLeagueResponse::class.java
            )).thenReturn(response)

            Mockito.verify(view).hideLoading()
            Mockito.verify(view).showSpinner(response.leagues)
        }*/
    }

    class MainPresenter(private val view: MainView,
                        private val apiRepository: ApiRepository,
                        private val gson: Gson,
                        private val context: CoroutineContextProvider = CoroutineContextProvider()) {

        fun getAllLeague() {
            view.showLoading()

            GlobalScope.launch(context.main) {
                val data = gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getAllLeague()).await(),
                        AllLeagueResponse::class.java
                )

                view.hideLoading()
                view.showSpinner(data.leagues)
            }
        }
    }

    class TestContextProvider : CoroutineContextProvider() {
        @ExperimentalCoroutinesApi
        override val main: CoroutineContext = Dispatchers.Unconfined
    }
}

open class CoroutineContextProvider {
    open val main: CoroutineContext by lazy { Dispatchers.Main }
}