package com.example.github.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.github.R
import com.example.github.databinding.FragmentSearchUsernameBinding

class UsernameSearchFragment : Fragment(R.layout.fragment_search_username) {
    private lateinit var binding: FragmentSearchUsernameBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchUsernameBinding.bind(view)

        binding.apply {
            icBackRepoSearch.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}