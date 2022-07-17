package com.example.nasa_api.view.animation

import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionSet
import android.view.View
import android.view.animation.AnticipateOvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.example.nasa_api.databinding.ActivityAnimationBinding


class AnimationActivity : AppCompatActivity() {


    var isFlag = false

    private lateinit var binding: ActivityAnimationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button.setOnClickListener{
            isFlag = !isFlag
            val myAutoTransition =  TransitionSet()
            myAutoTransition.ordering = TransitionSet.ORDERING_TOGETHER
            val fade = Fade()
            val changeBounds = ChangeBounds()
            myAutoTransition.addTransition(fade)
            myAutoTransition.addTransition(changeBounds)
            TransitionManager
                .beginDelayedTransition(binding.transitionsContainer,myAutoTransition)
            binding.text.visibility =if(isFlag) View.VISIBLE else{View.GONE}
        }

    }
}