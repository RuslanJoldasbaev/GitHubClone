package com.example.github.ui.search

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.github.MainActivity
import com.example.github.R
import com.example.github.databinding.FragmentSearchRepositoryBinding
import com.example.github.presentation.SearchViewModel
import com.example.github.ui.adapters.RepositoryAdapter
import com.example.github.utils.toast
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositorySearchFragment : Fragment(R.layout.fragment_search_repository) {
    private lateinit var binding: FragmentSearchRepositoryBinding
    private val adapter = RepositoryAdapter()
    private val navArgs: RepositorySearchFragmentArgs by navArgs()
    private val viewModel by viewModel<SearchViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchRepositoryBinding.bind(view)

        val searchValue = navArgs.textSearch

        lifecycleScope.launchWhenResumed {
            viewModel.searchRepositoriesByRepositoryName(searchValue)
        }

        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.recyclerView.adapter = adapter

        binding.apply {
            icBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
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