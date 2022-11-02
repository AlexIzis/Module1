package com.example.module1.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.module1.FragmentNavigation
import com.example.module1.ItemMarginDecoration
import com.example.module1.JsonParser
import com.example.module1.R
import com.example.module1.event.EventFragment
import com.example.module1.filter.FilterFragment

class NewsFragment : Fragment() {
    private lateinit var newsList: List<NewsUIModel>
    private var category = arrayListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewNews)
        val adapter = NewsAdapter(onItemClick())
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(ItemMarginDecoration())
        newsList =
            JsonParser(
                getString(R.string.path_to_news),
                NewsUIModel::class.java,
                requireContext()
            ).parseJson()
        adapter.differ.submitList(filterByCategories())

        val imageFilter: ImageView = view.findViewById(R.id.iconFilter)
        imageFilter.setOnClickListener {
            FragmentNavigation().addFragment(
                parentFragmentManager,
                R.id.fragmentContainerView,
                FilterFragment()
            )
        }

        activity?.supportFragmentManager?.setFragmentResultListener(
            "result",
            viewLifecycleOwner
        ) { _, bundle ->
            category = bundle.getStringArrayList("category")!!
            adapter.differ.submitList(filterByCategories())
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
}