package com.example.github.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.github.MainActivity
import com.example.github.R
import com.example.github.data.local.LocalStorage
import com.example.github.databinding.FramentSplashBinding
import kotlinx.coroutines.delay

class SplashFragment : Fragment(R.layout.frament_splash) {
    private lateinit var binding: FramentSplashBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FramentSplashBinding.bind(view)

        if (LocalStorage().isLog) {
            lifecycleScope.launchWhenResumed {
                delay(200)
                findNavController().navigate(
                    SplashFragmentDirections.actionSplashFragmentToHomeFragment()
                )
            }
        } else {
            lifecycleScope.launchWhenResumed {
                delay(200)
                findNavController().navigate(
                    SplashFragmentDirections.actionSplashFragmentToLoginFragment()
                )
            }
        }

    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).visilityOfBottomNavigation(View.GONE)
    }
}