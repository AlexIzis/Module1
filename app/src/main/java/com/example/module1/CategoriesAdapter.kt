package com.example.module1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    private val itemList = mutableListOf<CategoriesItem>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.image_category)
        val text: TextView = itemView.findViewById(R.id.text_category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.categories_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tmpItem = itemList[position]
        holder.img.setImageResource(tmpItem.img)
        holder.text.text = tmpItem.text
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setInfo(tmpArr: ArrayList<CategoriesItem>) {
        itemList.clear()
        itemList.addAll(tmpArr)
        notifyDataSetChanged()
    }
}
