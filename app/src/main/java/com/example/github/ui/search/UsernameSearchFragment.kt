package com.example.github.ui.search

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.github.R
import com.example.github.databinding.FragmentSearchUsernameBinding
import com.example.github.presentation.SearchViewModel

class UsernameSearchFragment : Fragment(R.layout.fragment_search_username) {
    private lateinit var binding: FragmentSearchUsernameBinding
    private lateinit var viewModel: SearchViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchUsernameBinding.bind(view)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[SearchViewModel::class.java]


        binding.apply {
            icBackRepoSearch.setOnClickListener {
                findNavController().popBackStack()
            }

            searchRepo.addTextChangedListener {
                val text = it.toString()
                val login = "%$text%"
                lifecycleScope.launchWhenResumed {
                    viewModel.searchUsersByUsername(login)
                }
            }
        }
    }
}