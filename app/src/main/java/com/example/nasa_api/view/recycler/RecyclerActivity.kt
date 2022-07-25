package com.example.nasa_api.view.recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.nasa_api.R
import com.example.nasa_api.databinding.ActivityMainBinding
import com.example.nasa_api.databinding.ActivityRecyclerBinding
import com.example.nasa_api.view.picture.PictureOfTheDayFragment


class RecyclerActivity : AppCompatActivity() {


    private lateinit var binding: ActivityRecyclerBinding

    private val data = arrayListOf(
        Pair(Data(id=0,"Заголовок", type = TYPE_HEADER), false),
        Pair(Data(id=1,"Earth", type = TYPE_EARTH), false),
        Pair(Data(id=2,"Earth", type = TYPE_EARTH), false),
        Pair(Data(id=3,"Mars", type = TYPE_MARS), false),
        Pair(Data(id=4,"Earth", type = TYPE_EARTH), false),
        Pair(Data(id=5,"Earth", type = TYPE_EARTH), false),
        Pair(Data(id=6,"Earth", type = TYPE_EARTH), false),
        Pair(Data(id=7,"Mars", type = TYPE_MARS), false)
    )
    lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = RecyclerAdapter(data, callbackAdd, callbackRemoveItem)


        binding.recyclerView.adapter = adapter


        ItemTouchHelper(ItemTouchHelperCallback(adapter)).attachToRecyclerView(binding.recyclerView)


    }

    private val callbackAdd = AddItem {
        data.add(it, Pair(Data(0,"Mars(New)", type = TYPE_MARS),false))
        adapter.setListDataAdd(data, it)
    }
    private val callbackRemoveItem = RemoveItem {
        data.removeAt(it)
        adapter.setListDataRemove(data, it)

    }

}