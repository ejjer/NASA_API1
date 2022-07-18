package com.example.nasa_api.view.animation


import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Explode
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.example.nasa_api.R
import com.example.nasa_api.databinding.ActivityAnimationBinding


class AnimationActivity : AppCompatActivity() {


    var isFlag = false

    private lateinit var binding: ActivityAnimationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isFlag = !isFlag


        binding.recyclerView.adapter=Adapter()





    }

    inner class Adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

            return MyViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.activity_animation_explode_recycler_view_item,
                    parent,
                false
                ) as View
            )

        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            holder.itemView.setOnClickListener{

                val rect = Rect()
                it.getGlobalVisibleRect(rect)
                val explode = Explode()
                explode.duration = 1500L
                explode.epicenterCallback = object : Transition.EpicenterCallback(){
                    override fun onGetEpicenter(transition: Transition): Rect {
                       return rect
                    }
                }
                TransitionManager.beginDelayedTransition(binding.recyclerView,explode)
                binding.recyclerView.adapter = null

            }
        }

        override fun getItemCount(): Int {
            return 32
        }

       inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {}
    }
}