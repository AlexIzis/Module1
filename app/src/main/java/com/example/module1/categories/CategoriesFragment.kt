package com.example.module1.categories

import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.module1.ItemMarginDecoration
import com.example.module1.R
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager

class CategoriesFragment : Fragment() {
    private val adapter = CategoriesAdapter()
    private val viewModel: CategoriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewHelp)
        recyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(ItemMarginDecoration())

        val loading: ProgressBar = view.findViewById(R.id.progressBarCategories)
        loading.visibility = View.GONE
        adapter.setCategories(viewModel.listCategories)
    }
}
