package com.example.github.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.github.R
import com.example.github.databinding.FragmentProfileRepoBinding
import com.example.github.presentation.MainViewModel
import com.example.github.ui.adapters.ProfileRepositoryAdapter
import com.example.github.utils.toast
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepoProfileFragment : Fragment(R.layout.fragment_profile_repo) {
    private lateinit var binding: FragmentProfileRepoBinding
    private val adapter = ProfileRepositoryAdapter()
    private val viewModel by viewModel<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileRepoBinding.bind(view)

        binding.recyclerView.adapter = adapter

        lifecycleScope.launchWhenResumed {
            viewModel.getUserRepositories()
        }

        binding.apply {
            icBackRepoProf.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        initObservers()
    }

    private fun initObservers() {
        viewModel.getUserRepositoriesFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)

        viewModel.messageFlow.onEach {
            toast("Mag'liwmat kelmey qaldi")
        }.launchIn(lifecycleScope)
    }
}