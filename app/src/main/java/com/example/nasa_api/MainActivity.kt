package com.example.nasa_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nasa_api.databinding.ActivityMainBinding
import com.example.nasa_api.view.picture.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if(savedInstanceState==null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container,PictureOfTheDayFragment.newInstance())
                .commit()
        }

    }
}