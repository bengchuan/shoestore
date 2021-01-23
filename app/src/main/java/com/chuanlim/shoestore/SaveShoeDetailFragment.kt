package com.chuanlim.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.chuanlim.shoestore.databinding.FragmentSaveShoeDetailsBinding
import com.chuanlim.shoestore.models.Shoe
import com.chuanlim.shoestore.models.ShoeListingViewModel

/**
 * Saving new shoe details fragment
 * ShoeListingFragment > SaveShoeDetailFragment
 */
class SaveShoeDetailFragment : Fragment() {
    private lateinit var binding: FragmentSaveShoeDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSaveShoeDetailsBinding.inflate(layoutInflater)

        val viewModel: ShoeListingViewModel by activityViewModels()
        binding.shoesViewModel = viewModel
        // Bind the view to the data
        binding.shoe = Shoe()
        binding.lifecycleOwner = this

        // Setting 'save' button onclick
        binding.saveShoeBtn.setOnClickListener {
            addShoeToList()
        }
        return binding.root
    }

    // Add Shoe to the ShoeList
    private fun addShoeToList() {
        val viewModel: ShoeListingViewModel by activityViewModels()
        binding.apply {
            shoe?.name = editTextShoeName.text.toString()
            shoe?.company = editTextCompany.text.toString()
            shoe?.size = editTextSize.text.toString().toDouble()
            shoe?.description = editTextDescription.text.toString()
        }
        viewModel.saveShoeDetails(binding.shoe!!)
        findNavController().navigate(
            SaveShoeDetailFragmentDirections.actionSaveShoeDetailFragmentToShoeListingFragment()
        )
    }
}