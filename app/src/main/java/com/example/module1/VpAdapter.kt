package com.example.module1

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class VpAdapter(fa: Search, private val listFR: List<Fragment>) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return listFR.size
    }

    override fun createFragment(position: Int): Fragment {
        return listFR[position]
    }
}
