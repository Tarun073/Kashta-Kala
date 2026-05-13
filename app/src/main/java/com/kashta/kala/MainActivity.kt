package com.kashta.kala

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.kashta.kala.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)

        // Fix: re-selecting same tab goes back to home
        binding.bottomNavigation.setOnItemReselectedListener { }

        // Control Bottom Navigation visibility
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigation.isVisible = destination.id != R.id.authFragment
            
            // Fix: bottom nav stays in sync with back button
            binding.bottomNavigation.menu.findItem(destination.id)?.isChecked = true
        }
    }

    // Fix: pressing back on any tab goes to home first
    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        if (navController.currentDestination?.id != R.id.homeFragment && 
            navController.currentDestination?.id != R.id.authFragment) {
            navController.navigate(R.id.homeFragment)
        } else {
            super.onBackPressed()
        }
    }
}