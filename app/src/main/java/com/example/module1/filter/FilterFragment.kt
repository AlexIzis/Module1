package com.example.module1.filter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewFilter)
        val adapter = FilterCategoriesAdapter(onItemClick())
        val listFromJson =
            JsonParser(getString(R.string.path_to_categories), CategoryUiModel::class.java, requireContext()).parseJson()
        adapter.setCategories(listFromJson)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(ItemMarginDecoration())


        val backArrow: ImageView = view.findViewById(R.id.back_arrow_to_news)
        backArrow.setOnClickListener {
            loadFragment(NewsFragment())
            FragmentNavigation().addFragment(
                parentFragmentManager,
                R.id.fragmentContainerView,
                NewsFragment()
            )
        }
    }

    private fun loadFragment(fr: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fr)
        fragmentTransaction.commit()
    }

    private fun onItemClick() = { category: CategoryUiModel ->
        val bundle = Bundle()
        bundle.putString("category", category.value)
        val fragment = NewsFragment()
        fragment.arguments = bundle
        activity?.supportFragmentManager?.setFragmentResult("result", bundleOf("category" to category.value))
        activity?.supportFragmentManager?.popBackStack()
        Unit
    }
}