package com.chuanlim.shoestore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.chuanlim.shoestore.databinding.ActivityMainBinding

/**
 * MainActivity where all fragments are created
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var controller:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Step 5: inflate layout with View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        controller = findNavController(R.id.myNavHostFragment)
        // Adding an AppBarConfiguration
        NavigationUI.setupActionBarWithNavController(this, controller)

        // set navigation change listener
        controller.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.welcomeFragment, R.id.shoeListingFragment -> {
                    // Disabling the 'Back' button
                    getSupportActionBar()?.setDisplayHomeAsUpEnabled(false);
                    getSupportActionBar()?.setHomeButtonEnabled(false);
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return controller.navigateUp()
    }
}
