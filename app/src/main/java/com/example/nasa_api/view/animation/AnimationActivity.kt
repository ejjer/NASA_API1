package com.example.nasa_api.view.animation


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nasa_api.databinding.ActivityAnimationBinding


class AnimationActivity : AppCompatActivity() {


    var isFlag = false
    var duration = 1000L

    private lateinit var binding: ActivityAnimationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.scrollView.setOnScrollChangeListener{v,scrollY,oldScrollX,oldScrollY ->
            binding.header.isSelected = binding.scrollView.canScrollVertically(-1)
        }





    }
}