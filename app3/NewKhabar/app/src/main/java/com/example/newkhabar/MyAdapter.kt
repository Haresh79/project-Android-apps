package com.example.newkhabar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter(val news: List<Article>) : RecyclerView.Adapter<MyAdapter.viewHolder>() {
    private lateinit var myListener: onItemClickListener
    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setItemClickListener(Listener:onItemClickListener){
        myListener=Listener
    }

    class viewHolder(view: View, listener: onItemClickListener):RecyclerView.ViewHolder(view){
        val desc=view.findViewById<TextView>(R.id.about)
        val name=view.findViewById<TextView>(R.id.name)
        val pic=view.findViewById<ImageView>(R.id.imageView)
        init {
            view.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)
        return viewHolder(view, myListener)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.name.text= news[position].publishedAt.toString()
        holder.desc.text= news[position].title.toString()
        Picasso.get().load(news[position].urlToImage).into(holder.pic)
    }

    override fun getItemCount(): Int {
       return news.size
    }


}
