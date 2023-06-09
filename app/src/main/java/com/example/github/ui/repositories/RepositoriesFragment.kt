package com.example.github.ui.repositories

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.github.MainActivity
import com.example.github.R
import com.example.github.databinding.FragmentRepositoriesBinding
import com.example.github.presentation.MainViewModel
import com.example.github.ui.adapters.RepositoryAdapter
import com.example.github.utils.toast
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoriesFragment : Fragment(R.layout.fragment_repositories) {
    private lateinit var binding: FragmentRepositoriesBinding
    private val adapter = RepositoryAdapter()
    private val viewModel by viewModel<MainViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRepositoriesBinding.bind(view)

        lifecycleScope.launchWhenResumed {
            viewModel.getUserRepositories()
        }

        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.recyclerView.adapter = adapter

        binding.apply {
            icBackRepo.setOnClickListener {
                findNavController().popBackStack()
            }
        }

    }

    private fun initObservers() {
        viewModel.getUserRepositoriesFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)

        viewModel.messageFlow.onEach {
            toast("Mag'liwmat kelmey qaldi")
        }.launchIn(lifecycleScope)
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).visilityOfBottomNavigation(View.GONE)
    }
}