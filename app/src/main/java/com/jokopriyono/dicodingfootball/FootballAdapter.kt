package com.jokopriyono.dicodingfootball

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_football.view.*

class FootballAdapter(private val context: Context, private val items: List<Item>, private val listener: (Item) -> Unit):
        RecyclerView.Adapter<FootballAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_football, parent, false))

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bindItem(item: Item, listener: (Item) -> Unit){
            itemView.name.text = item.name
            item.image?.let { Picasso.get().load(it).into(itemView.image) }
            itemView.setOnClickListener { listener(item) }
        }
    }
}