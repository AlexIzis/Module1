package com.example.module1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private val searchResults = mutableListOf<String>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val result: TextView = itemView.findViewById(R.id.textViewSearchItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tmpItem = searchResults[position]
        holder.result.text = tmpItem
    }

    override fun getItemCount(): Int {
        return searchResults.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setResults(tmpArr: List<String>) {
        searchResults.clear()
        searchResults.addAll(tmpArr)
        notifyDataSetChanged()
    }
}
