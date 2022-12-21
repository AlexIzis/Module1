package com.example.module1.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.module1.FragmentNavigation
import com.example.module1.ItemMarginDecoration
import com.example.module1.R
import com.example.module1.event.EventFragment
import com.example.module1.filter.FilterFragment
import com.example.module1.filter.FilterFragment.Companion.KEY_FROM_FILTER
import com.example.module1.filter.FilterFragment.Companion.REQUEST_KEY_FILTER
import kotlinx.coroutines.launch

class NewsFragment : Fragment() {
    private var category = arrayListOf<String>()
    private val adapter = NewsAdapter(onItemClick())
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(
            this,
            NewsViewModelFactory(NewsStoreImpl(requireContext()))
        )[NewsViewModel::class.java]
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val loading: ProgressBar = view.findViewById(R.id.progressBarNews)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewNews)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(ItemMarginDecoration())

        val imageFilter: ImageView = view.findViewById(R.id.iconFilter)
        imageFilter.setOnClickListener {
            FragmentNavigation().addFragment(
                parentFragmentManager,
                R.id.fragmentContainerView,
                FilterFragment()
            )
        }

        lifecycleScope.launch {
            viewModel.emitNewsList()
            viewModel.newsFlow.collect {
                if (it.isNotEmpty()) {
                    loading.visibility = View.GONE
                }
                adapter.differ.submitList(it)
            }
        }

        activity?.supportFragmentManager?.setFragmentResultListener(
            REQUEST_KEY_FILTER,
            viewLifecycleOwner
        ) { _, bundle ->
            category = requireNotNull(bundle.getStringArrayList(KEY_FROM_FILTER))
            adapter.differ.submitList(filterByCategories())
        }
    }

    private fun onItemClick() = { news: NewsUIModel ->
        FragmentNavigation().addFragment(
            parentFragmentManager,
            R.id.fragmentContainerView,
            EventFragment.getInst(news)
        )
    }

    private fun filterByCategories(): List<NewsUIModel> {
        return if (category.isEmpty()) {
            viewModel.newsFlow.value
        } else {
            category.flatMap { cat -> viewModel.newsFlow.value.filter { news -> news.categories.contains(cat)} }
        }
    }
}
