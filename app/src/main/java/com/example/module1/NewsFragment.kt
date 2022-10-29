package com.example.module1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NewsFragment : Fragment() {
    private lateinit var newsList: List<NewsUIModel>
    private var category: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getString("category").toString()
        }
    }

    private fun filterByCategories(): List<NewsUIModel> {
        return if (category == "") newsList
        else {
            newsList.filter { it.categories.contains(category) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    private fun loadFragment(fr: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fr)
        fragmentTransaction.commit()
    }

    private fun onItemClick() = { news: NewsUIModel ->
        val bundle = Bundle()
        bundle.putString("img", news.img)
        bundle.putString("label", news.label)
        bundle.putString("desc", news.description)
        bundle.putLong("time", news.time)
        bundle.putString("org", news.organization)
        bundle.putString("address", news.address)
        bundle.putStringArray("numList", news.numberList.toTypedArray())
        bundle.putString("email", news.email)
        bundle.putStringArray("imgOpt", news.imgOpt.toTypedArray())
        bundle.putString("site", news.site)
        val fragment = EventFragment()
        fragment.arguments = bundle
        loadFragment(fragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewNews)
        val adapter = NewsAdapter(onItemClick())
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(ItemMarginDecoration())
        newsList =
            JsonParser("news.json", NewsUIModel::class.java, requireContext()).parseJson()
        adapter.differ.submitList(filterByCategories())

        val imageFilter: ImageView = view.findViewById(R.id.icon_filter)
        imageFilter.setOnClickListener {
            loadFragment(FilterFragment())
        }
    }
}