package com.jokopriyono.dicodingfootball.feature.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jokopriyono.dicodingfootball.api.response.AllLeague
import com.jokopriyono.dicodingfootball.api.response.Team
import org.jetbrains.anko.*

class DetailActivity : AppCompatActivity(), DetailView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val data: AllLeague = intent.getParcelableExtra("data")
        DetailActivityUI("", "").setContentView(this)
    }
    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    class DetailActivityUI(private val nama: String?, private val desc: String?):
            AnkoComponent<DetailActivity>{
        override fun createView(ui: AnkoContext<DetailActivity>) = with(ui){
            verticalLayout{
                padding = dip(16)
                /*this.lparams(width = matchParent, height = matchParent){
                    gravity = Gravity.CENTER
                    padding = dip(16)
                }
                imageView{
                    resImage?.let { Picasso.get().load(resImage).into(this) }
                }.lparams(width = matchParent, height = dip(150))

                textView(nama){
                    this.gravity = Gravity.CENTER
                    textSize = 20f
                }.lparams(width = matchParent){
                    gravity = Gravity.CENTER
                }

                textView(desc){
                    this.gravity = Gravity.CENTER
                    textSize = 11f
                }.lparams(width = matchParent){
                    gravity = Gravity.CENTER
                }*/
            }
        }

    }
}