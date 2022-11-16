package com.example.module1.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.module1.R
import com.example.module1.news.NewsUIModel

class SearchEventsAdapter: RecyclerView.Adapter<SearchEventsAdapter.ViewHolder>() {
    private var events = mutableListOf<NewsUIModel>()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.textViewSearchItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tmpItem = events[position]
        holder.title.text = tmpItem.label
    }

    override fun getItemCount(): Int {
        return events.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setResults(tmpArr: List<NewsUIModel>) {
        events.clear()
        events.addAll(tmpArr)
        notifyDataSetChanged()
    }
}