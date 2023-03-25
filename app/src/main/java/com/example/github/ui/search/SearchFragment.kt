package com.example.github.ui.search

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.github.R
import com.example.github.databinding.FragmentSearchBinding

class SearchFragment : Fragment(R.layout.fragment_search) {
    private lateinit var binding: FragmentSearchBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchBinding.bind(view)

        binding.apply {
            linearSearch1.visibility = View.GONE
            linearSearch2.visibility = View.GONE

            icBack.setOnClickListener {
                findNavController().popBackStack()
            }

            searchMenu.addTextChangedListener {
                linearSearch1.visibility = View.VISIBLE
                linearSearch2.visibility = View.VISIBLE

                val searchText = it.toString()
                linearSearch1.setOnClickListener {
                    findNavController().navigate(
                        SearchFragmentDirections.actionSearchFragmentToRepositorySearchFragment(
                            searchText
                        )
                    )
                }

                linearSearch2.setOnClickListener {
                    findNavController().navigate(
                        SearchFragmentDirections.actionSearchFragmentToUsernameSearchFragment(
                            searchText
                        )
                    )
                }
            }

        }

    }
}