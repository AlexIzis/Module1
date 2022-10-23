package com.example.module1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.*

class CategoriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewHelp)
        val adapter = CategoriesAdapter()
        val layoutManager = FlexboxLayoutManager(context).apply {
            justifyContent = JustifyContent.SPACE_BETWEEN
            alignItems = AlignItems.CENTER
            flexDirection = FlexDirection.ROW
            flexWrap = FlexWrap.WRAP
        }
        recyclerView.layoutManager = layoutManager
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
