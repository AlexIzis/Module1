package com.example.module1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewHelp) ?: return
        val adapter = CategoriesAdapter()
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(ItemMarginDecoration())
        adapter.setCategories(
            listOf(
                CategoryUiModel(R.drawable.little, getString(R.string.children)),
                CategoryUiModel(R.drawable.dad, getString(R.string.adult)),
                CategoryUiModel(R.drawable.granny, getString(R.string.old)),
                CategoryUiModel(R.drawable.cat, getString(R.string.animals)),
                CategoryUiModel(R.drawable.event, getString(R.string.events))
            )
        )
    }
}
