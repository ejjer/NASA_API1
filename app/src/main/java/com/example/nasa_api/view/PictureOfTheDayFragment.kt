package com.example.nasa_api.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.nasa_api.R
import com.example.nasa_api.ViewModel.AppState
import com.example.nasa_api.ViewModel.PictureOfTheDayViewModel
import com.example.nasa_api.databinding.FragmentPictureOfTheDayBinding


class PictureOfTheDayFragment : Fragment() {

    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(
            viewLifecycleOwner
        ) { appState ->
            renderData(appState)
        }
        viewModel.sendRequest()

    }

    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.Error -> {}
            AppState.Loading -> {}
            is AppState.Success -> {
                binding.imageView.load(appState.pictureOfTheDayResponseData.url) //койл настроить загрузку изображений
            }
        }
    }

    companion object {

        fun newInstance() =
            PictureOfTheDayFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}