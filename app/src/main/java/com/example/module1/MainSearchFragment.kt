package com.example.module1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainSearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val fragmentNKO = SearchFragmentNKO.newInstance("SecondFr", "SearchAct")

        val fragList = listOf(
            SearchFragmentEvents.newInstance("firstFr", "SearchAct"),
            fragmentNKO
        )

        val fragListTitle = listOf(
            getString(R.string.by_events),
            getString(R.string.on_NKO)
        )

        val adapter = VpAdapter(this, fragList)
        val viewPager: ViewPager2 = view.findViewById(R.id.viewPager)
        val tabLayout: TabLayout = view.findViewById(R.id.tabLayout)
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { item, pos ->
            item.text = fragListTitle[pos]
        }.attach()

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    fragmentNKO.updateDate()
                }
            }
        })
    }
}
