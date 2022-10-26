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
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
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

    private fun onItemClick() = { news: NewsUIModel->
        /*val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, EventFragment())
        fragmentTransaction.commit()*/
        val bundle = Bundle()
        bundle.putString("label", news.label)
        val fragment = EventFragment()
        fragment.arguments = bundle
        loadFragment(fragment)
        //Unit
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewNews)
        val adapter = NewsAdapter(onItemClick())
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(ItemMarginDecoration())
        adapter.setNews(
            listOf(
                NewsUIModel(
                    R.drawable.avatar_1,
                    "Спонсоры отремонтируют школу-интернат",
                    "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области",
                    "Осталось 13 дней (21.09 – 20.10)"
                ),
                NewsUIModel(
                    R.drawable.avatar_1,
                    "Конкурс по вокальному пению в детском доме №6",
                    "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области",
                    "Октябрь 20, 2016"
                )
            )
        )

        val imageFilter: ImageView = view.findViewById(R.id.icon_filter)
        imageFilter.setOnClickListener {
            /*val fragmentManager = parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainerView, FilterFragment())
            fragmentTransaction.commit()*/
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