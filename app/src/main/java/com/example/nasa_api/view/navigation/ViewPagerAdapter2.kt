package com.example.nasa_api.view.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter2(fa:FragmentActivity):FragmentStateAdapter(fa) {


    private val fragments = arrayOf( MarsFragment(), SystemFragment())


    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}