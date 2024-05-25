package com.example.noteit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val notes: ArrayList<note>) : RecyclerView.Adapter<MyAdapter.viewHolder>() {

    private lateinit var myListener:onItemClickListener
    interface onItemClickListener{
        fun onItemClicked(note: note)
    }
    fun setItemClickListener(Listener:onItemClickListener){
        myListener=Listener
    }

    class viewHolder(view: View):RecyclerView.ViewHolder(view){
        val textNote=view.findViewById<TextView>(R.id.note)
        val delBtn= view.findViewById<ImageView>(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = viewHolder(LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false))
        view.delBtn.setOnClickListener {
            myListener.onItemClicked(notes[view.adapterPosition])
        }
        return view
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.textNote.text=notes[position].note

    }

}
