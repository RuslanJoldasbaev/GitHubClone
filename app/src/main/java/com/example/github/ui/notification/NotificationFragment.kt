package com.example.github.ui.notification

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.github.R
import com.example.github.databinding.FragmentNotificationBinding

class NotificationFragment : Fragment(R.layout.fragment_notification) {
    private lateinit var binding: FragmentNotificationBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNotificationBinding.bind(view)

    }
}