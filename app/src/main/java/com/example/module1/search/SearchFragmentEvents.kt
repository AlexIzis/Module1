package com.example.module1.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.module1.JsonParser
import com.example.module1.R
import com.example.module1.news.NewsUIModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchFragmentEvents : Fragment() {
    private var news: ArrayList<NewsUIModel> = arrayListOf()
    private lateinit var unsubscribeNews: Disposable
    private val viewModel: VMSearch by activityViewModels()

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

        val imgSearch: ImageView = view.findViewById(R.id.imageView5)
        val textIfNoResults: TextView = view.findViewById(R.id.textViewAnnotation)
        unsubscribeNews = Observable.fromCallable {
            JsonParser(
                getString(R.string.path_to_news),
                NewsUIModel::class.java,
                requireContext()
            ).parseJson()
        }
            .subscribeOn(Schedulers.computation())
            .map { it as ArrayList<NewsUIModel> }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                news = it
                imgSearch.visibility = View.GONE
                textIfNoResults.visibility = View.GONE
                adapter.setResults(news)
            }

        CoroutineScope(Dispatchers.Main).launch {
            try {
                /*SearchFlow.outputFlow().collect {
                    adapter.setResults(searchSystem(it))
                }*/
                viewModel.searchFlow.collect{
                    adapter.setResults(searchSystem(it))
                }
            } catch (e: Exception) {
                Log.d("tag", e.toString())
                Log.d("tag", "Программка, не болей")
            }
        }
    }

    private fun searchSystem(search: String): ArrayList<NewsUIModel> {
        return news.filter { it.label.contains(search) } as ArrayList<NewsUIModel>
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unsubscribeNews.dispose()
    }
}
