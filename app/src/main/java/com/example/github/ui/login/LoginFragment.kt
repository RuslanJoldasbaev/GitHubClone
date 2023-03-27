package com.example.github.ui.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.github.R
import com.example.github.data.local.LocalStorage
import com.example.github.databinding.FragmentLoginBinding
import com.example.github.presentation.MainViewModel
import com.example.github.presentation.SearchViewModel
import com.example.github.utils.toast
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModel<MainViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

        initListeners()
    }

    private fun initListeners() {
        binding.apply {
            tilUrl.visibility = View.GONE
            etUrl.visibility = View.GONE
            signIn.visibility = View.GONE
            enterpriseText.visibility = View.GONE
            signInEnterprise.visibility = View.VISIBLE
            signInWith.visibility = View.VISIBLE

            signInWith.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://github.com/login/oauth/authorize?client_id=8f3cf5f09bd0c93a0528&scope=repo")
                )
                startActivity(intent)
            }

            signInEnterprise.setOnClickListener {
                signInWith.visibility = View.GONE
                signInEnterprise.visibility = View.GONE
                tilUrl.visibility = View.VISIBLE
                etUrl.visibility = View.VISIBLE
                signIn.visibility = View.VISIBLE
                enterpriseText.visibility = View.VISIBLE
            }

        }
    }

    override fun onResume() {
        super.onResume()
        val uri: Uri? = requireActivity().intent?.data
        if (uri != null) {
            val code = uri.getQueryParameter("code")
            if (code != null) {
                //get Access Token zapros ketedi codedi alg'annan son'
                toast("Login success: $code")
                LocalStorage().code = code
                lifecycleScope.launchWhenResumed {
                    viewModel.getAccessToken()
                }
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                )
            } else if ((uri.getQueryParameter("error")) != null) {
                toast("Something went wrong!")
            }
            initObservers()
        }

    }

    private fun initObservers() {
        viewModel.getAccessTokenFlow.onEach {
            LocalStorage().isLog = true
            LocalStorage().token = it
        }.launchIn(lifecycleScope)

        viewModel.messageFlow.onEach {
            toast("Token kelmey qaldi")
        }.launchIn(lifecycleScope)
    }
}