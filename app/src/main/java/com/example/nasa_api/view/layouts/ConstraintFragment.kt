package com.example.nasa_api.view.layouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nasa_api.databinding.FragmentConstraintBinding

class ConstraintFragment : Fragment() {
    private var _binding: FragmentConstraintBinding? = null
    private val binding: FragmentConstraintBinding
        get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConstraintBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      //  binding.group1.visibility = View.GONE


    }
    companion object {
        @JvmStatic
        fun newInstance() = ConstraintFragment()
    }
}