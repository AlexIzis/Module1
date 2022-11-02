package com.example.module1.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.module1.ItemMarginDecoration
import com.example.module1.JsonParser
import com.example.module1.R
import com.google.android.flexbox.*
import java.util.concurrent.Executors

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

        val executor = Executors.newSingleThreadExecutor()
        val loading: ProgressBar = view.findViewById(R.id.progressBarCategories)
        executor.execute {
            Thread.sleep(5000)
            val listFromJson =
                JsonParser(
                    getString(R.string.path_to_categories),
                    CategoryUiModel::class.java,
                    requireContext()
                ).parseJson()
            activity?.runOnUiThread {
                adapter.setCategories(listFromJson)
                loading.visibility = View.GONE
            }
        }
    }
}
