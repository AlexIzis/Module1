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
import com.google.gson.Gson

class NewsFragment : Fragment() {
    private lateinit var newsList: List<NewsUIModel>
    private var category: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getString("category").toString()
        }
    }

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
            val fragmentManager = parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.add(R.id.fragmentContainerView, FilterFragment())
            fragmentTransaction.commit()
        }

        activity?.supportFragmentManager?.setFragmentResultListener(
            "result",
            viewLifecycleOwner
        ) { _, bundle ->
            category = bundle.getString("category").toString()
            adapter.differ.submitList(filterByCategories())
        }
    }

    private fun onItemClick() = { news: NewsUIModel ->
        val bundle = Bundle()
        bundle.putString("new", Gson().toJson(news))
        val fragment = EventFragment()
        fragment.arguments = bundle
        FragmentNavigation().addFragment(
            parentFragmentManager,
            R.id.fragmentContainerView,
            fragment
        )
    }

    private fun filterByCategories(): List<NewsUIModel> {
        return if (category == "") newsList
        else {
            newsList.filter { it.categories.contains(category) }
        }
    }
}