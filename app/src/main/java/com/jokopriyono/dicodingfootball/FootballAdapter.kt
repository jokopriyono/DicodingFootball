package com.jokopriyono.dicodingfootball

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_football.view.*
import org.jetbrains.anko.startActivity

class FootballAdapter(val context: Context, private val items: List<Item>, private val listener: (Item) -> Unit):
        RecyclerView.Adapter<FootballAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_football, parent, false))

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bindItem(item: Item, context: Context){
            itemView.txt_name.text = item.name
            item.image?.let { Picasso.get().load(it).into(itemView.img_image) }
            itemView.cardview.setOnClickListener {
                view: View? -> context.startActivity<DetailActivity>("data" to item)
            }
        }
    }
}