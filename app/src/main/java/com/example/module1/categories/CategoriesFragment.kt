package com.example.module1.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    //функция расширение, увидел на форуме решил попробовать, но не помогло
    private fun Fragment?.runOnUIThread(action: Runnable) {
        this ?: return
        if (!isAdded) return
        activity?.runOnUiThread(action)
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
        val listFromJson =
            JsonParser(
                getString(R.string.path_to_categories),
                CategoryUiModel::class.java,
                requireContext()
            ).parseJson()
        adapter.setCategories(listFromJson)

        TODO("Поток запускается, но в момент вызова функции runOnUIThread выбрасывается исключение Fragment not attached to a context")
        /*val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            Thread.sleep(2000)
            val listFromJson =
                JsonParser(
                    getString(R.string.path_to_categories),
                    CategoryUiModel::class.java,
                    requireContext()
                ).parseJson()
            runOnUIThread{
                adapter.setCategories(listFromJson)
            }
        }*/
    }
}
