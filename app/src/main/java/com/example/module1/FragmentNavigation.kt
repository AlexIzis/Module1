package com.example.module1

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentNavigation {
    fun addFragment(fragmentManager: FragmentManager, fragmentContainer: Int, fragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack("...")
        fragmentTransaction.setCustomAnimations(
            com.google.android.material.R.anim.abc_popup_enter,
            com.google.android.material.R.anim.abc_popup_exit)
        fragmentTransaction.add(fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    fun replaceFragment(fragmentManager: FragmentManager, fragmentContainer: Int, fragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(fragmentContainer, fragment)
        fragmentTransaction.commit()
    }
}