package com.chuanlim.shoestore.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * ViewModel to store Shoes item which will be used by ShoeList and Save Shoe details Fragments.
 */
class ShoeListingViewModel : ViewModel() {
    private final val TAG = "ShoeListingViewModel"

    //Shoe Name
    val name = MutableLiveData<String>("")

    //Shoe Size
    val size = MutableLiveData<Double>(0.0)

    //Company
    val company = MutableLiveData<String>("")

    // Description
    val description = MutableLiveData<String>("")

    // To enable save btn or not
    private val _saveBtnEnabled = MutableLiveData<Boolean>(false)
    val saveBtnEnabled: LiveData<Boolean>
        get() = _saveBtnEnabled

    // Logic to check the all details has been entered
    fun checkSaveBtn() {
        Log.i(TAG, "Checking the Save Btn ")

        _saveBtnEnabled.value = name.value?.isNotEmpty()!! &&
                size.value!! > 0.0 &&
                company.value?.isNotEmpty()!! &&
                description.value?.isNotEmpty()!!

    }

    // List of shoes
    // This is used by ShoeListingFragment
    private val _shoes = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoes: LiveData<MutableList<Shoe>>
        get() = _shoes

    // Save Shoe Details
    fun saveShoeDetails() {
        val shoe = Shoe(
            name = name.value!!,
            size = size.value!!,
            company = company.value!!,
            description = description.value!!
        )
        _shoes.value?.add(shoe)
    }

    // Clear reset shoe details after the SaveShoeDetailsFragment is destroyed
    fun clearShoeDetails() {
        Log.i(TAG, "Resetting the shoe details")
        name.value = ""
        size.value = 0.0
        company.value = ""
        description.value = ""
    }
}
