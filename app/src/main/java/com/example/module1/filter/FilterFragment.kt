package com.example.module1.filter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.module1.FragmentNavigation
import com.example.module1.ItemMarginDecoration
import com.example.module1.JsonParser
import com.example.module1.news.NewsFragment
import com.example.module1.R
import com.example.module1.categories.CategoryUiModel

class FilterFragment : Fragment() {

    private val categories = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /*val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewFilter)
        val adapter = FilterCategoriesAdapter(onItemClick())*/
        val listFromJson =
            JsonParser(
                getString(R.string.path_to_categories),
                CategoryUiModel::class.java,
                requireContext()
            ).parseJson()

        val checkBoxChildren: CheckBox = view.findViewById(R.id.checkChildren)
        checkBoxChildren.text = listFromJson[0].text
        checkBoxChildren.setOnClickListener {
            categories.add("children")
        }

        val checkBoxAdults: CheckBox = view.findViewById(R.id.checkAdults)
        checkBoxAdults.text = listFromJson[1].text
        checkBoxAdults.setOnClickListener {
            categories.add("adults")
        }

        val checkBoxElderly: CheckBox = view.findViewById(R.id.checkElderly)
        checkBoxElderly.text = listFromJson[2].text
        checkBoxElderly.setOnClickListener {
            categories.add("elderly")
        }

        val checkBoxAnimals: CheckBox = view.findViewById(R.id.checkAnimals)
        checkBoxAnimals.text = listFromJson[3].text
        checkBoxAnimals.setOnClickListener {
            categories.add("animals")
        }

        val checkBoxEvents: CheckBox = view.findViewById(R.id.checkEvents)
        checkBoxEvents.text = listFromJson[4].text
        checkBoxEvents.setOnClickListener {
            categories.add("events")
        }


        /*adapter.setCategories(listFromJson)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(ItemMarginDecoration())*/

        val okButton: ImageView = view.findViewById(R.id.sentToNews)
        okButton.setOnClickListener {
            activity?.supportFragmentManager?.setFragmentResult(
                "result",
                bundleOf("category" to categories[0])
            )
            parentFragmentManager.popBackStack()
        }

        val backArrow: ImageView = view.findViewById(R.id.backArrowToNews)
        backArrow.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    /*private fun onItemClick() = { category: CategoryUiModel ->
        activity?.supportFragmentManager?.setFragmentResult(
            "result",
            bundleOf("category" to category.value)
        )
        activity?.supportFragmentManager?.popBackStack()
        Unit
    }*/
}