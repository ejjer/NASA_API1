package com.example.nasa_api.view.picture

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.text.*
import android.text.style.BulletSpan
import android.text.style.ForegroundColorSpan
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
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
import com.example.nasa_api.Repository.PictureOfTheDayResponseData
import java.lang.System.load


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


           }




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

    @SuppressLint("NewApi")
    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
            }
            AppState.Loading -> {
            }
            is AppState.Success -> {
                binding.imageView.load(appState.pictureOfTheDayResponseData.url) {

                }




                binding.textView.text = appState.pictureOfTheDayResponseData.explanation

                binding.textView.typeface = Typeface.createFromAsset(requireActivity().assets,"folder/az_Eret1.ttf")

                val spanned:Spanned
                val spannableString:SpannableString
                val spannableStringBuilder:SpannableStringBuilder

                val bulletSpanOne = BulletSpan(20,ContextCompat.getColor(requireContext(),R.color.Grey))
                val bulletSpanSecond = BulletSpan(20,ContextCompat.getColor(requireContext(),R.color.Grey))
                ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.Grey))

                val text = "My text \nbullet one\nbullet two"

                spannableString = SpannableString(text)

                spannableString.setSpan(bulletSpanOne,9,20,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                spannableString.setSpan(bulletSpanSecond,21,spannableString.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                for (i in text.indices){
                    if(text[i] == 't'){
                        spannableString.setSpan(
                            ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.Grey)),
                        i,i+1,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    }
                }
                binding.textView.text = text
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
//        binding.chipToday.setOnClickListener {
//            Toast.makeText(requireContext(), "chipToday", Toast.LENGTH_SHORT).show()
//        }
//        binding.chipYesterday.setOnClickListener {
//            Toast.makeText(requireContext(), "chipYesterday", Toast.LENGTH_SHORT).show()
//        }
//        binding.chipDayBeforeYesterday.setOnClickListener {
//            Toast.makeText(requireContext(), "chipDayBeforeYesterday", Toast.LENGTH_SHORT).show()
//        }

        binding.textInputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputText.text.toString()}")
            })
        }


    }
}