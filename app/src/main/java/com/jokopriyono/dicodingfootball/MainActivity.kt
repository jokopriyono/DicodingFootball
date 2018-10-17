package com.jokopriyono.dicodingfootball

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {
    private var items: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()

    }

    private fun initData(){
        val name = resources.getStringArray(R.array.club_name)
        val image = resources.obtainTypedArray(R.array.club_image)
        val desc = resources.getStringArray(R.array.club_desc)
        items.clear()
        for (i in name.indices){
            items.add(Item(name[i], image.getResourceId(i, 0), desc[i]))
        }
        val adapter = FootballAdapter(
                applicationContext,
                items){
            startActivity<DetailActivity>("nama" to "Test put extra")
        }

        image.recycle()
        MainActivityUI(adapter).setContentView(this)
    }

    class MainActivityUI(private val footballAdapter: FootballAdapter) : AnkoComponent<MainActivity> {
        companion object {
            val recycler = 1
        }
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui){
            verticalLayout {
                lparams(matchParent, matchParent)
                recyclerView {
                    val orientation = LinearLayoutManager.VERTICAL
                    layoutManager = LinearLayoutManager(context, orientation, true)
                    overScrollMode = View.OVER_SCROLL_NEVER
                    adapter = footballAdapter
                }.lparams(width = matchParent, height = matchParent)
            }
        }
    }
}
