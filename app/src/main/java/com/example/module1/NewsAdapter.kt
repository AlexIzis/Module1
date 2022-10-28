package com.example.module1

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsAdapter(private val onItemClick:  ((NewsUIModel) -> Unit)?) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private val news = mutableListOf<NewsUIModel>()
    private lateinit var context: Context

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgView: ImageView = itemView.findViewById(R.id.image_container)
        val labelText: TextView = itemView.findViewById(R.id.text_container)
        val descText: TextView = itemView.findViewById(R.id.desc_container)
        val time: TextView = itemView.findViewById(R.id.time_container)
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(news[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        context = parent.context
        return ViewHolder(itemView)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = news[position]
        holder.imgView.setImageResource(
            context.resources.getIdentifier(
                news.img,
                "img",
                context.packageName
            )
        )
        holder.labelText.text = news.label
        holder.descText.text = news.description
        holder.time.text = news.time
    }

    override fun getItemCount(): Int {
        return news.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNews(items: List<NewsUIModel>){
        news.clear()
        news.addAll(items)
        notifyDataSetChanged()
    }
}