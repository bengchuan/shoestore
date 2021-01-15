package com.chuanlim.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.chuanlim.shoestore.databinding.FragmentWelcomeBinding

/**
 * Showing Welcome page for First time user
 * LoginFragment > WelcomeFragment
 */
class WelcomeFragment: Fragment() {
    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeBinding.inflate(layoutInflater)
        // Add onclick listener
        binding.welcomeNextButton.setOnClickListener {
            findNavController().navigate(R.id.instructionFragment)
        }
        return binding.root
    }
}