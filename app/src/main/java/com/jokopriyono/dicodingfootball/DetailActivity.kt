package com.jokopriyono.dicodingfootball

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intentNama: String = intent.getStringExtra("nama")
        val intentImage: Int = intent.getIntExtra("image", 0)
        val intentDesc: String = intent.getStringExtra("desc")
        DetailActivityUI(intentNama, intentImage, intentDesc).setContentView(this)
    }

    class DetailActivityUI(private val nama: String, private val resImage: Int, private val desc: String):
            AnkoComponent<DetailActivity>{
        override fun createView(ui: AnkoContext<DetailActivity>) = with(ui){
            verticalLayout{
                padding = dip(16)
                /*this.lparams(width = matchParent, height = matchParent){
                    gravity = Gravity.CENTER
                    padding = dip(16)
                }*/
                imageView{
                    Picasso.get().load(resImage).into(this)
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
                }
            }
        }

    }
}
