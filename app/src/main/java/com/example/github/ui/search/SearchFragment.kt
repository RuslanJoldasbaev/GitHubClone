package com.example.github.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.github.R
import com.example.github.data.local.LocalStorage
import com.example.github.databinding.FragmentSearchBinding
import com.example.github.presentation.MainViewModel
import com.example.github.presentation.SearchViewModel
import com.example.github.ui.adapters.RepositoryAdapter
import com.example.github.ui.adapters.RepositoryAdapterProfile

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
            Log.d("TTTT", LocalStorage().token)

            searchRepository.addTextChangedListener {
                val text = it.toString()
                val searchValue = "%$text%"
                lifecycleScope.launchWhenResumed {
                    viewModel.searchRepositoriesByRepositoryName(searchValue)
                }
            }
        }
    }
}