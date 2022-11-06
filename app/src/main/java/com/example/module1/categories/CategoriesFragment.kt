package com.example.module1.categories

import android.os.Bundle
import android.os.Parcelable
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
import java.util.ArrayList
import java.util.concurrent.Executors

const val CATEGORY_LIST = "list_of_categories"

class CategoriesFragment : Fragment() {
    private var listFromJson: ArrayList<CategoryUiModel> = ArrayList()

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
        if (savedInstanceState != null) {
            listFromJson =
                savedInstanceState.getParcelableArrayList<CategoryUiModel>(CATEGORY_LIST) as ArrayList<CategoryUiModel>
            adapter.setCategories(listFromJson)
            loading.visibility = View.GONE
        } else {
            executor.execute {
                Thread.sleep(5000)
                listFromJson =
                    JsonParser(
                        getString(R.string.path_to_categories),
                        CategoryUiModel::class.java,
                        requireContext()
                    ).parseJson() as ArrayList<CategoryUiModel>
                activity?.runOnUiThread {
                    adapter.setCategories(listFromJson)
                    loading.visibility = View.GONE
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(
            CATEGORY_LIST,
            listFromJson as ArrayList<out Parcelable>
        )
    }
}
