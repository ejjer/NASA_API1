package com.example.nasa_api.view.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.nasa_api.R
import com.example.nasa_api.databinding.ActivityBottomBarBinding
import com.example.nasa_api.databinding.ActivityViewPagerBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class BottomBarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomBarBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener {
           when (it.itemId){
               R.id.action_view_earth ->{navigateTo(EarthFragment())}
               R.id.action_view_mars ->{navigateTo(MarsFragment())}
               R.id.action_view_system ->{navigateTo(SystemFragment())}
           }
            true
        }
        binding.bottomNavigationView.selectedItemId =  R.id.action_view_earth


    }

    private fun navigateTo(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    }




