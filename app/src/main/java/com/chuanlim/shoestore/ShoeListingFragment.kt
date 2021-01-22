package com.chuanlim.shoestore

import android.os.Bundle
import android.view.*
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.chuanlim.shoestore.databinding.FragmentShoeListingBinding
import com.chuanlim.shoestore.models.Shoe
import com.chuanlim.shoestore.models.ShoeListingViewModel

/**
 * Showing Added Shoes
 */
class ShoeListingFragment : Fragment() {

    private lateinit var binding: FragmentShoeListingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoeListingBinding.inflate(layoutInflater)

        //set Logout
        setHasOptionsMenu(true)

        binding.addShoeBtn.setOnClickListener {
            findNavController().navigate(ShoeListingFragmentDirections.actionShoeListingFragmentToSaveShoeDetailFragment())
        }

        val viewModel: ShoeListingViewModel by activityViewModels()
        viewModel.shoes.observe(viewLifecycleOwner) { shoes -> loadShoes(shoes) }

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuLogout -> findNavController().navigate(
                ShoeListingFragmentDirections.actionShoeListingFragmentToLoginFragment(
                    false
                )
            )
        }

        return super.onOptionsItemSelected(item)
    }

    /**
     * Loading all the shoes into the Fragment
     */
    private fun loadShoes(shoes: List<Shoe>) {
        if (shoes.isNotEmpty()) {
            binding.labelEmptyShoesListing.isGone = true
            shoes.forEach { i ->
                val shoeItemLayout = ShoeItem(requireContext())
                shoeItemLayout.populateLayoutWith(i)
                // Add item to linear layout
                binding.shoesListingContainer.addView(shoeItemLayout)
            }
        } else {
            binding.labelEmptyShoesListing.isVisible = true
        }
    }
}