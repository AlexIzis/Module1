package com.example.module1.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.module1.FragmentNavigation
import com.example.module1.ItemMarginDecoration
import com.example.module1.R
import com.example.module1.event.EventFragment
import com.example.module1.event.EventFragment.Companion.KEY_NEW
import com.example.module1.filter.FilterFragment
import com.example.module1.filter.FilterFragment.Companion.KEY_FROM_FILTER
import com.example.module1.filter.FilterFragment.Companion.REQUEST_KEY_FILTER
import kotlinx.coroutines.launch

class NewsFragment : Fragment() {
    private var category = arrayListOf<String>()
    private val adapter = NewsAdapter(onItemClick())
    private lateinit var loading: ProgressBar
    private lateinit var viewModel: NewsViewModel
    private val vm: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*viewModel = ViewModelProvider(
            this,
            NewsViewModelFactory(*//*NewsStoreImpl()*//*)
        )[NewsViewModel::class.java]*/
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loading = view.findViewById(R.id.progressBarNews)

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
            val storeImpl = NewsStoreImpl()
            storeImpl.getNews(vm)
            vm.newsFlow.collect {
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
        val bundle = Bundle()
        bundle.putParcelable(KEY_NEW, news)
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
            vm.newsFlow.value
        } else {
            val filterNews = arrayListOf<NewsUIModel>()
            for (i in category) {
                filterNews.addAll(vm.newsFlow.value.filter { it.categories.contains(i) })
            }
            filterNews.toList()
        }
    }
}
