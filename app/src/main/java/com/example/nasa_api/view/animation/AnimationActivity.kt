package com.example.nasa_api.view.animation


import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*
import com.example.nasa_api.R
import com.example.nasa_api.databinding.ActivityAnimationBinding


class AnimationActivity : AppCompatActivity() {


    var isFlag = false

    private lateinit var binding: ActivityAnimationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.imageView.setOnClickListener {
            isFlag = !isFlag

            val params = it.layoutParams as LinearLayout.LayoutParams

            val transitionSet = TransitionSet()
            val changeImageTransform = ChangeImageTransform()
            val changeBounds = ChangeBounds()

            transitionSet.addTransition(changeBounds)
            transitionSet.addTransition(changeImageTransform)

            changeBounds.duration = 1000L
            changeImageTransform.duration = 1000L

            TransitionManager.beginDelayedTransition(binding.root, transitionSet)
            if (isFlag) {
                params.height = LinearLayout.LayoutParams.MATCH_PARENT
                (it as ImageView).scaleType = ImageView.ScaleType.CENTER_CROP
            } else {
                params.height = LinearLayout.LayoutParams.WRAP_CONTENT
                (it as ImageView).scaleType = ImageView.ScaleType.CENTER_INSIDE
            }
            it.layoutParams = params


        }


    }
}