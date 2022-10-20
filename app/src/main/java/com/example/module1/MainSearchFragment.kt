package com.example.module1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainSearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainSearchFragment : Fragment() {
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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val fr = SearchFragment2.newInstance("SecondFr", "SearchAct")

        val fragList = listOf(
            SearchFragment1.newInstance("firstFr", "SearchAct"),
            fr
        )

        val fragListTitle = listOf(
            getString(R.string.by_events),
            getString(R.string.on_NKO)
        )

        val adapter = VpAdapter(this, fragList)
        val viewPager: ViewPager2 = getView()?.findViewById(R.id.viewPager) ?: return
        val tb: TabLayout = getView()?.findViewById(R.id.tabLayout) ?: return
        viewPager.adapter = adapter
        TabLayoutMediator(tb, viewPager) { item, pos ->
            item.text = fragListTitle[pos]
        }.attach()

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    fr.updateDate()
                }
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainSearchFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainSearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
