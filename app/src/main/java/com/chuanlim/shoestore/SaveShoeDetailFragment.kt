package com.chuanlim.shoestore

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.chuanlim.shoestore.databinding.FragmentSaveShoeDetailsBinding
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
        binding.lifecycleOwner = this

        // Unable to find 'doAfterTextChanged' attribute in layout
        // this is what we can do at the moment.
        binding.apply {
            editTextShoeName.doAfterTextChanged {
                viewModel.name.value = it.toString()
                viewModel.checkSaveBtn()
            }
            editTextSize.doAfterTextChanged {
                viewModel.size.value = it.toString().toDouble()
                viewModel.checkSaveBtn()
            }
            editTextCompany.doAfterTextChanged {
                viewModel.company.value = it.toString()
                viewModel.checkSaveBtn()
            }
            editTextDescription.doAfterTextChanged {
                viewModel.description.value = it.toString()
                viewModel.checkSaveBtn()
            }
        }

        // Setting 'save' button onclick
        binding.saveShoeBtn.setOnClickListener {
            viewModel.saveShoeDetails()
            // clear the shoe details
            viewModel.clearShoeDetails()
            findNavController().navigate(SaveShoeDetailFragmentDirections.actionSaveShoeDetailFragmentToShoeListingFragment())
        }

        return binding.root
    }

    /**
     * Ensuring the view model data is reset
     */
    override fun onDestroyView() {
        super.onDestroyView()
        val viewModel: ShoeListingViewModel by activityViewModels()
        viewModel.clearShoeDetails()
        Log.i("SaveShoe", "Clearing the shoe details.")
    }
}