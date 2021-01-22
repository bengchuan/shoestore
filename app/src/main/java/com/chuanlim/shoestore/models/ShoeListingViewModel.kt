package com.chuanlim.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * ViewModel to store Shoes item which will be used by ShoeList and Save Shoe details Fragments.
 */
class ShoeListingViewModel : ViewModel() {
    // List of shoes
    // This is used by ShoeListingFragment
    private val _shoes = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoes: LiveData<MutableList<Shoe>>
        get() = _shoes

    // Save Shoe Details
    fun saveShoeDetails(shoe: Shoe) {
        _shoes.value?.add(shoe)
    }
}
