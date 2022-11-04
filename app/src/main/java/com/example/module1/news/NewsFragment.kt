package com.example.module1.news

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.module1.FragmentNavigation
import com.example.module1.ItemMarginDecoration
import com.example.module1.R
import com.example.module1.event.EventFragment
import com.example.module1.filter.FilterFragment

class NewsFragment : Fragment() {
    private var newsList: ArrayList<NewsUIModel> = ArrayList()
    private var category = arrayListOf<String>()
    private val adapter = NewsAdapter(onItemClick())
    private lateinit var loading: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loading = view.findViewById(R.id.progressBarNews)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewNews)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(ItemMarginDecoration())

        val intentToService = Intent(activity, LoadNewsService::class.java)
        val myReceiver = MyBroadcastReceiver()
        val intentFilter = IntentFilter("Load_News")
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT)
        activity?.registerReceiver(myReceiver, intentFilter)

        val imageFilter: ImageView = view.findViewById(R.id.iconFilter)
        imageFilter.setOnClickListener {
            FragmentNavigation().addFragment(
                parentFragmentManager,
                R.id.fragmentContainerView,
                FilterFragment()
            )
        }

        if (savedInstanceState != null){
            val result = savedInstanceState.getParcelableArrayList<NewsUIModel>("list_of_news")
            if(result != null){
                newsList = result
                loading.visibility = View.GONE
                adapter.differ.submitList(result)
            }
        }
        if (newsList.size == 0){
            activity?.startService(intentToService)
        }

        activity?.supportFragmentManager?.setFragmentResultListener(
            "result",
            viewLifecycleOwner
        ) { _, bundle ->
            category = bundle.getStringArrayList("category")!!
            adapter.differ.submitList(filterByCategories())
        }
    }

    inner class MyBroadcastReceiver : BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            val result = p1?.getParcelableArrayListExtra<NewsUIModel>("categories")
            if (result != null){
                newsList = result
                loading.visibility = View.GONE
                adapter.differ.submitList(filterByCategories())
            }
        }

    }

    private fun onItemClick() = { news: NewsUIModel ->
        val bundle = Bundle()
        bundle.putParcelable("new", news)
        val fragment = EventFragment()
        fragment.arguments = bundle
        FragmentNavigation().addFragment(
            parentFragmentManager,
            R.id.fragmentContainerView,
            fragment
        )
    }

    private fun filterByCategories(): List<NewsUIModel> {
        return if (category.isEmpty()) {
            newsList
        } else {
            val filterNews = arrayListOf<NewsUIModel>()
            for (i in category){
                filterNews.addAll(newsList.filter { it.categories.contains(i) })
            }
            filterNews.toList()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (newsList.isNotEmpty()) {
            outState.putParcelableArrayList("list_of_news", newsList)
        }
    }
}