package com.example.module1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsFragment : Fragment() {
    private lateinit var newsList: List<NewsUIModel>
    private lateinit var category: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        category = ""
        arguments?.let {
            category = it.getString("category").toString()
        }
    }

    private fun filterByCategories(): List<NewsUIModel> {
        return if (category == "") newsList
        else {
            newsList.filter { it.categories.contains(category) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    private fun loadFragment(fr: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fr)
        fragmentTransaction.commit()
    }

    private fun onItemClick() = { news: NewsUIModel ->
        val bundle = Bundle()
        bundle.putInt("id", news.id)
        bundle.putString("img", news.img)
        bundle.putString("label", news.label)
        bundle.putString("desc", news.description)
        bundle.putString("time", news.time.toString())
        bundle.putString("org", news.organization)
        bundle.putString("address", news.address)
        bundle.putStringArray("numList", news.numberList.toTypedArray())
        bundle.putString("email", news.email)
        bundle.putStringArray("imgOpt", news.imgOpt.toTypedArray())
        bundle.putString("site", news.site)
        val fragment = EventFragment()
        fragment.arguments = bundle
        loadFragment(fragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewNews)
        val adapter = NewsAdapter(onItemClick())
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(ItemMarginDecoration())
        newsList =
            JsonParser("news.json", NewsUIModel::class.java, requireContext()).parseJson()
        adapter.differ.submitList(filterByCategories())

        val imageFilter: ImageView = view.findViewById(R.id.icon_filter)
        imageFilter.setOnClickListener {
            loadFragment(FilterFragment())
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewsFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}