package com.example.github.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.github.R
import com.example.github.databinding.FragmentProfileBinding
import com.example.github.presentation.MainViewModel
import com.example.github.ui.adapters.RepositoryAdapter
import com.example.github.ui.adapters.RepositoryAdapterProfile
import com.example.github.utils.toast
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private lateinit var binding: FragmentProfileBinding
    private val adapter = RepositoryAdapterProfile()
    private lateinit var viewModel: MainViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[MainViewModel::class.java]

        binding = FragmentProfileBinding.bind(view)

        initListeners()
        initObservers()
    }

    private fun initListeners() {

        binding.apply {

            profileRecyclerview.adapter = adapter

            lifecycleScope.launchWhenResumed {
                viewModel.getUserProfileInfo()
                viewModel.getUserRepositories()
            }

        }
    }

    private fun initObservers() {
        viewModel.getUserProfileInfoFlow.onEach {
            binding.apply {
                textName.text = it.name
                textUsername.text = it.login
                textFollowerNumber.text = it.followers.toString()
                textFollowingNumber.text = it.following.toString()
                textProfileItemNum1.text = it.public_repos.toString()
                Glide.with(this@ProfileFragment)
                    .load(it.avatar_url)
                    .into(profileImg)
            }
        }.launchIn(lifecycleScope)

        viewModel.getUserRepositoriesFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)

        viewModel.messageFlow.onEach {
            toast("Mag'liwmat kelmey qaldi")
        }
    }
}