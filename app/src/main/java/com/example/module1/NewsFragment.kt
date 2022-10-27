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

    private fun onItemClick() = { news: NewsUIModel ->
        val bundle = Bundle()
        bundle.putInt("id", news.id)
        bundle.putInt("img", news.img)
        bundle.putString("label", news.label)
        bundle.putString("desc", news.description)
        bundle.putString("time", news.time)
        bundle.putString("org", news.organization)
        bundle.putString("address", news.address)
        bundle.putStringArray("numList", news.numberList.toTypedArray())
        bundle.putString("email", news.email)
        bundle.putIntArray("imgOpt", news.imgOpt.toIntArray())
        bundle.putString("site", news.site)
        bundle.putStringArray("categories", news.categories.toTypedArray())
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
        adapter.setNews(
            listOf(
                NewsUIModel(
                    0,
                    "Спонсоры отремонтируют школу-интернат",
                    R.drawable.avatar_1,
                    "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области",
                    "Осталось 13 дней (21.09 – 20.10)",
                    "Благотворительный Фонд «Счастливый Мир»",
                    "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208",
                    listOf("+7 (937) 037 37-73", "+7 (937) 016 16-16"),
                    "Напишите нам",
                    listOf(R.drawable.avatar_2, R.drawable.avatar_3),
                    "Перейти на сайт организаии",
                    listOf("children", "adults")
                ),
                NewsUIModel(
                    1,
                    "Конкурс по вокальному пению в детском доме №6",
                    R.drawable.avatar_2,
                    "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области",
                    "Октябрь 20, 2016",
                    "Благотворительный Фонд «Счастливый Мир»",
                    "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208",
                    listOf("+7 (937) 037 37-73", "+7 (937) 016 16-16"),
                    "Напишите нам",
                    listOf(R.drawable.avatar_1, R.drawable.avatar_3),
                    "Перейти на сайт организаии",
                    listOf("children", "adults", "elderly")
                )
            )
        )

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