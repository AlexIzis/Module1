package com.example.module1.categories

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.module1.R

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    private val categories = mutableListOf<CategoryUiModel>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgView: ImageView = itemView.findViewById(R.id.imageCategory)
        val textView: TextView = itemView.findViewById(R.id.textCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.categories_item, parent, false)
        return ViewHolder(itemView)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tmpItem = categories[position]
        val context = holder.imgView.context
        holder.imgView.setImageResource(
            context.resources.getIdentifier(
                tmpItem.img,
                "img",
                context.packageName
            )
        )
        holder.textView.text = tmpItem.text
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCategories(items: List<CategoryUiModel>) {
        categories.clear()
        categories.addAll(items)
        notifyDataSetChanged()
    }
}
