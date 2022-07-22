package com.example.nasa_api.view.recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nasa_api.R
import com.example.nasa_api.databinding.ActivityMainBinding
import com.example.nasa_api.databinding.ActivityRecyclerBinding
import com.example.nasa_api.view.picture.PictureOfTheDayFragment


class RecyclerActivity : AppCompatActivity() {


    private lateinit var binding: ActivityRecyclerBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = arrayListOf(
            Data("Header", type = TYPE_HEADER),
            Data("Earth", type = TYPE_EARTH),
            Data("Mars", type = TYPE_MARS),
            Data("Earth", type = TYPE_EARTH),
            Data("Earth", type = TYPE_EARTH),
            Data("Earth", type = TYPE_EARTH),
            Data("Mars", type = TYPE_MARS)
        )

        binding.recyclerView.adapter = RecyclerAdapter(data)


    }
}