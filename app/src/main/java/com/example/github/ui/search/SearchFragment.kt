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
import com.example.github.presentation.MainViewModel
import com.example.github.presentation.SearchViewModel
import com.example.github.ui.adapters.RepositoryAdapter
import com.example.github.utils.toast
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(R.layout.fragment_search) {
    private lateinit var binding: FragmentSearchBinding
    private val adapter = RepositoryAdapter()
    private val viewModel by viewModel<SearchViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchBinding.bind(view)


        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.apply {
            linearSearch1.visibility = View.GONE
            linearSearch2.visibility = View.GONE

            icBack.setOnClickListener {
                findNavController().popBackStack()
            }

            searchMenu.addTextChangedListener {
                val searchMenu = it.toString()
                linearSearch1.visibility = View.VISIBLE
                linearSearch2.visibility = View.VISIBLE

                linearSearch1.setOnClickListener {
                    findNavController().navigate(
                        SearchFragmentDirections.actionSearchFragmentToRepositorySearchFragment(searchMenu)
                    )
                }

                linearSearch2.setOnClickListener {
                    findNavController().navigate(
                        SearchFragmentDirections.actionSearchFragmentToRepositorySearchFragment(searchMenu)
                    )
                }
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