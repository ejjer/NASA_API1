package com.example.nasa_api.view.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nasa_api.BlueTheme
import com.example.nasa_api.GreenTheme
import com.example.nasa_api.MainActivity
import com.example.nasa_api.ViewModel.PictureOfTheDayViewModel
import com.example.nasa_api.databinding.FragmentSettingsBinding
import com.google.android.material.tabs.TabLayout

class SettingsFragment : Fragment() {



    private lateinit var parentActivity: MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentActivity = (context as MainActivity)
    }

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root

    }

    private val viewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        setTabLayoutClick()




    }

    private fun setTabLayoutClick()=with (binding){
        when((parentActivity.getCurrentTheme())){
            GreenTheme -> {
                tabLayout.selectTab(binding.tabLayout.getTabAt(0))
            }
            BlueTheme -> {
                tabLayout.selectTab(binding.tabLayout.getTabAt(1))
            }
        }
        with(tabLayout) {
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        0 -> {
                            parentActivity.setCurrentTheme(GreenTheme)
                            parentActivity.recreate()
                        }
                        1 -> {
                            parentActivity.setCurrentTheme(BlueTheme)
                            parentActivity.recreate()
                        }

                    }
                    requireActivity().recreate()
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    TODO("Not yet implemented")
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    //TODO("Not yet implemented")
                }
            })
        }

    }



    companion object {
        fun newInstance() = SettingsFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }







}