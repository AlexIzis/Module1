package com.example.module1.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.module1.AppClass
import com.example.module1.ItemMarginDecoration
import com.example.module1.R
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoriesFragment : Fragment() {
    private val adapter = CategoriesAdapter()
    private lateinit var viewModel: CategoriesViewModel

    @Inject
    lateinit var vmFactory: CategoriesViewModelFactory

    @Inject
    lateinit var storeImplInst: CategoryStore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (context?.applicationContext as AppClass).mainComponent.inject(this)

        viewModel = ViewModelProvider(
            this, vmFactory
        )[CategoriesViewModel::class.java]
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewHelp)
        val loading: ProgressBar = view.findViewById(R.id.progressBarCategories)
        recyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(ItemMarginDecoration())

        lifecycleScope.launch {
            viewModel.emitCategoriesList()
            viewModel.categoriesFlow.collect {
                if (it.isNotEmpty()) {
                    hideProgressBar(loading)
                } else {
                    viewModel.handleIntent(CategoriesIntent.StartScreen)
                }
                adapter.setCategories(it)
            }
        }
    }

    private fun hideProgressBar(loading: ProgressBar) {
        loading.visibility = View.GONE
    }
}
