package com.example.nasa_api.view.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.nasa_api.R
import com.example.nasa_api.databinding.ActivityBottomBarBinding


class BottomBarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomBarBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_view_earth -> {
                    navigateTo(EarthFragment())
                }
                R.id.action_view_mars -> {
                    navigateTo(MarsFragment())
                }
                R.id.action_view_system -> {
                    navigateTo(SystemFragment())
                }
            }
            true
        }
        binding.bottomNavigationView.selectedItemId = R.id.action_view_earth

        val badge = binding.bottomNavigationView.getOrCreateBadge(R.id.action_view_system)

        badge.maxCharacterCount = 2


    }

    private fun navigateTo(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }


}




