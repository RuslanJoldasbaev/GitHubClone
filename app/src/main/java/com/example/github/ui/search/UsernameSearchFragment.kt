package com.example.github.ui.search

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.github.MainActivity
import com.example.github.R
import com.example.github.databinding.FragmentSearchUsernameBinding
import com.example.github.presentation.SearchViewModel
import com.example.github.ui.adapters.UsernameSearchAdapter
import com.example.github.utils.toast
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsernameSearchFragment : Fragment(R.layout.fragment_search_username) {
    private lateinit var binding: FragmentSearchUsernameBinding
    private val adapter = UsernameSearchAdapter()
    private lateinit var viewModel: SearchViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[SearchViewModel::class.java]

        binding = FragmentSearchUsernameBinding.bind(view)

        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.recyclerView.adapter = adapter

        binding.apply {
            icBackRepoSearch.setOnClickListener {
                findNavController().popBackStack()
            }

            searchRepo.addTextChangedListener {
                val login = it.toString()
                lifecycleScope.launchWhenResumed {
                    viewModel.searchUsersByUsername(login)
                }
            }
        }
    }

    private fun initObservers() {
        viewModel.searchUsersByUsernameFlow.onEach {
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