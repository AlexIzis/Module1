package com.example.module1.filter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.module1.R
import com.example.module1.categories.CategoryUiModel

class FilterCategoriesAdapter(private val onItemClick: ((CategoryUiModel) -> Unit)?) :
    RecyclerView.Adapter<FilterCategoriesAdapter.ViewHolder>() {

    private val categoriesFilter = mutableListOf<CategoryUiModel>()
    private lateinit var context: Context


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgView: ImageView = itemView.findViewById(R.id.image_category_filter)
        val textView: TextView = itemView.findViewById(R.id.text_category_filter)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(categoriesFilter[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.categories_item_filter, parent, false)
        context = parent.context
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tmpItem = categoriesFilter[position]
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
        return categoriesFilter.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCategories(items: List<CategoryUiModel>) {
        categoriesFilter.clear()
        categoriesFilter.addAll(items)
        notifyDataSetChanged()
    }

}