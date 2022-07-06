package com.example.nasa_api.view.picture

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.nasa_api.MainActivity
import com.example.nasa_api.R
import com.example.nasa_api.ViewModel.AppState
import com.example.nasa_api.ViewModel.PictureOfTheDayViewModel
import com.example.nasa_api.databinding.FragmentPictureOfTheDayBinding
import com.example.nasa_api.view.drawer.BottomNavigationDrawerFragment
import com.example.nasa_api.view.picture.PictureOfTheDayFragment.Companion.newInstance
import com.example.nasa_api.view.settings.SettingsFragment


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

        click()


        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
        setHasOptionsMenu(true)   }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.bottom_app_bar,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.app_bar_settings->{requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .hide(this)
                .add(R.id.container,SettingsFragment.newInstance())
                .addToBackStack("")
                .commit()}
            R.id.app_bar_fav->{}
            android.R.id.home -> {
                activity?.let {
                    BottomNavigationDrawerFragment().show(it.supportFragmentManager, "tag")
                }
            }
        }
        return super.onOptionsItemSelected(item)
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
        _binding = null
    }

    fun click() {
        binding.chipToday.setOnClickListener {
            Toast.makeText(requireContext(), "chipToday", Toast.LENGTH_SHORT).show()
        }
        binding.chipYesterday.setOnClickListener {
            Toast.makeText(requireContext(), "chipYesterday", Toast.LENGTH_SHORT).show()
        }
        binding.chipDayBeforeYesterday.setOnClickListener {
            Toast.makeText(requireContext(), "chipDayBeforeYesterday", Toast.LENGTH_SHORT).show()
        }

        binding.textInputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputText.text.toString()}")
            })
        }


    }
}