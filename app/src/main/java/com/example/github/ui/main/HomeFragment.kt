package com.example.github.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.github.MainActivity
import com.example.github.R
import com.example.github.data.local.LocalStorage
import com.example.github.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        initListeners()
    }

    private fun initListeners() {
        binding.apply {

            linear4.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToRepositoriesFragment()
                )
            }

            search.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToUsernameSearchFragment()
                )
            }

            circlePlus.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToSearchFragment()
                )
            }
        }
    }


    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).visilityOfBottomNavigation(View.VISIBLE)
    }
}