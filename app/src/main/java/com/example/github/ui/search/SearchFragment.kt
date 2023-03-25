package com.example.github.ui.search

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.github.MainActivity
import com.example.github.R
import com.example.github.databinding.FragmentSearchBinding
import com.example.github.presentation.SearchViewModel
import com.example.github.ui.adapters.RepositoryAdapter
import com.example.github.utils.toast
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SearchFragment : Fragment(R.layout.fragment_search) {
    private lateinit var binding: FragmentSearchBinding
    private val adapter = RepositoryAdapter()
    private lateinit var viewModel: SearchViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchBinding.bind(view)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[SearchViewModel::class.java]

        binding.recyclerView.adapter = adapter

        binding.apply {
            icBack.setOnClickListener {
                findNavController().popBackStack()
            }

            searchRepository.addTextChangedListener {
                val text = it.toString()
                val searchValue = "%$text%"
                lifecycleScope.launchWhenResumed {
                    viewModel.searchRepositoriesByRepositoryName(searchValue)
                }
            }
        }

        initObservers()
    }

    private fun initObservers() {
        viewModel.searchRepositoriesByRepositoryNameFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)

        viewModel.messageFlow.onEach {
            toast("Mag'liwmat kelmey qaldi")
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).visilityOfBottomNavigation(View.GONE)
    }
}