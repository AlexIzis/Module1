package com.example.module1.news

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.module1.R
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter(private val onItemClick: ((NewsUIModel) -> Unit)) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgView: ImageView = itemView.findViewById(R.id.imageContainer)
        val labelText: TextView = itemView.findViewById(R.id.textContainer)
        val descText: TextView = itemView.findViewById(R.id.descContainer)
        val time: TextView = itemView.findViewById(R.id.timeContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return ViewHolder(itemView).apply {
            itemView.setOnClickListener {
                onItemClick.invoke(differ.currentList[adapterPosition])
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables", "SimpleDateFormat")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = differ.currentList[position]
        val context = holder.imgView.context
        /*holder.imgView.setImageResource(
            context.resources.getIdentifier(
                news.img,
                "img",
                context.packageName
            )
        )*/
        holder.labelText.text = news.label
        holder.descText.text = news.description
        holder.time.text = SimpleDateFormat("MMMM dd, yyyy").format(Date(news.time))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val differCallback = object : DiffUtil.ItemCallback<NewsUIModel>() {
        override fun areItemsTheSame(oldItem: NewsUIModel, newItem: NewsUIModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewsUIModel, newItem: NewsUIModel): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)
}