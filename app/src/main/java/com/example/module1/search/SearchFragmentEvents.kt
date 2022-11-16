package com.example.module1.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.module1.JsonParser
import com.example.module1.R
import com.example.module1.news.NewsUIModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchFragmentEvents : Fragment() {
    private var news: ArrayList<NewsUIModel> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_search_events, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewSearchEvents)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = SearchEventsAdapter()
        recyclerView.adapter = adapter
        news = JsonParser(
            getString(R.string.path_to_news),
            NewsUIModel::class.java,
            requireContext()
        ).parseJson() as ArrayList<NewsUIModel>
        val imgSearch: ImageView = view.findViewById(R.id.imageView5)
        val textIfNoResults: TextView = view.findViewById(R.id.textViewAnnotation)
        if (news.size > 0) {
            imgSearch.visibility = View.GONE
            textIfNoResults.visibility = View.GONE
            adapter.setResults(news)
        }

        SearchBus.listen().subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                adapter.setResults(searchSystem(it))
            }
    }

    private fun searchSystem(search: String): ArrayList<NewsUIModel> {
        return news.filter { it.label.contains(search) } as ArrayList<NewsUIModel>
    }
}
