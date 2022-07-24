package com.example.nasa_api.view.recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nasa_api.R
import com.example.nasa_api.databinding.ActivityMainBinding
import com.example.nasa_api.databinding.ActivityRecyclerBinding
import com.example.nasa_api.view.picture.PictureOfTheDayFragment


class RecyclerActivity : AppCompatActivity() {


    private lateinit var binding: ActivityRecyclerBinding

    private val data = arrayListOf(
        Data("Header", type = TYPE_HEADER),
        Data("Earth", type = TYPE_EARTH),
        Data("Mars", type = TYPE_MARS),
        Data("Earth", type = TYPE_EARTH),
        Data("Earth", type = TYPE_EARTH),
        Data("Earth", type = TYPE_EARTH),
        Data("Mars", type = TYPE_MARS)
    )
    lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter=RecyclerAdapter(data,callbackAdd,callbackRemoveItem)


       binding.recyclerView.adapter = adapter


    }

    private val callbackAdd = AddItem {
        data.add(it,Data("Mars(New)", type = TYPE_MARS))
        adapter.setListDataAdd(data,it)
    }
    private val callbackRemoveItem = RemoveItem  {
        data.removeAt(it)
        adapter.setListDataRemove(data,it)

    }

}