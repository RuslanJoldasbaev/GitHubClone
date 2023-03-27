package com.example.github.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.github.R
import com.example.github.databinding.FragmentProfileBinding
import com.example.github.presentation.MainViewModel
import com.example.github.ui.adapters.RepositoryAdapterProfile
import com.example.github.utils.toast
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private lateinit var binding: FragmentProfileBinding
    private val adapter = RepositoryAdapterProfile()
    private val viewModel by viewModel<MainViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileBinding.bind(view)

        lifecycleScope.launchWhenResumed {
            viewModel.getUserProfileInfo()
            viewModel.getUserRepositories()
        }

        initListeners()
        initObservers()
    }

    private fun initListeners() {

        binding.apply {

            profileRecyclerview.adapter = adapter

            linearProf1.setOnClickListener {
                findNavController().navigate(
                    ProfileFragmentDirections.actionProfileFragmentToRepoProfileFragment()
                )
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
                Glide.with(profileImg)
                    .load(it.avatar_url)
                    .into(profileImg)
            }
        }.launchIn(lifecycleScope)

        viewModel.getUserRepositoriesFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)

        viewModel.messageFlow.onEach {
            toast("Mag'liwmat kelmey qaldi")
        }.launchIn(lifecycleScope)
    }
}