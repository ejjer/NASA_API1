package com.example.nasa_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nasa_api.databinding.ActivityMainBinding
import com.example.nasa_api.view.picture.PictureOfTheDayFragment

const val GreenTheme  = 1
const val BlueTheme  = 2


class MainActivity : AppCompatActivity() {

    private val KEY_SP = "sp"
    private val KEY_CURRENT_THEME = "current_theme"


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if(savedInstanceState==null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commit()
        }

    }

    fun setCurrentTheme(currentTheme: Int) {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_CURRENT_THEME, currentTheme)
        editor.apply()
    }



    fun getCurrentTheme(): Int {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        return sharedPreferences.getInt(KEY_CURRENT_THEME, -1)
    }

    private fun getRealStyle(currentTheme: Int): Int {
        return when (currentTheme) {
            BlueTheme -> R.style.BlueTheme
            GreenTheme -> R.style.GreenTheme

            else -> 0
        }
    }
}