package com.example.nasa_api.view.animation


import android.graphics.Rect
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*
import com.example.nasa_api.R
import com.example.nasa_api.databinding.ActivityAnimationBinding


class AnimationActivity : AppCompatActivity() {


    var isFlag = true

    private lateinit var binding: ActivityAnimationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.button.setOnClickListener {
            isFlag = !isFlag

            val params = it.layoutParams as FrameLayout.LayoutParams

            val changeBounds = ChangeBounds()
            changeBounds.duration = 2000L
            changeBounds.setPathMotion(ArcMotion())
            TransitionManager.beginDelayedTransition(binding.root,changeBounds)
            if(isFlag){
                params.gravity = Gravity.TOP or Gravity.START
            }
            else{
                params.gravity = Gravity.BOTTOM or Gravity.END
            }
            binding.button.layoutParams = params




        }


    }
}