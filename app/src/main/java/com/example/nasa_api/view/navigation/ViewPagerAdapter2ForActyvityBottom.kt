package com.example.nasa_api.view.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nasa_api.view.picture.PictureOfTheDayFragment

class ViewPagerAdapter2ForEarthFragment(fr:Fragment):FragmentStateAdapter(fr) {


    private val fragments = arrayOf( PictureOfTheDayFragment.newInstance(), PictureOfTheDayFragment.newInstance())


    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}