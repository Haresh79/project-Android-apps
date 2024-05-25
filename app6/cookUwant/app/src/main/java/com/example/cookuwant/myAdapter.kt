package com.example.cookuwant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class myAdapter(var data:ArrayList<fooditem>):RecyclerView.Adapter<myAdapter.viewHolder>() {

    private lateinit var mylistener:onItemClickListener

    interface onItemClickListener {
        fun onItemClicked(position: Int)
    }
    fun setItemClickListener(Listener:onItemClickListener){
        mylistener=Listener
    }

    class viewHolder(view: View, listener: onItemClickListener):RecyclerView.ViewHolder(view) {
        val poster=view.findViewById<ImageView>(R.id.imageview)
        val title=view.findViewById<TextView>(R.id.titleitem)
        init {
            view.setOnClickListener {
                listener.onItemClicked(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return viewHolder(view,mylistener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        Picasso.get().load(data[position].poster).into(holder.poster)
        holder.title.text=data[position].title
    }
}