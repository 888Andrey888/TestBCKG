package com.example.testbckg.presentation.activitys.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.testbckg.R
import com.example.testbckg.databinding.ActivityMainBinding
import com.example.testbckg.presentation.activitys.auth.AuthActivity
import com.example.testbckg.utils.replaceActivity
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()
        initBottomNavigation()
        initFields()
        initFunc()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.pop_up_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.pop_up_btn_exit -> {
                Toast.makeText(this, "click on btn exit", Toast.LENGTH_SHORT).show()
                lifecycleScope.launch {
                    viewModel.signOutUser()
                    initFunc()
                }
            }
        }
        return true
    }

    private fun initBottomNavigation() {
        binding.bottomNavMenu.setupWithNavController(
            navController = binding.navHostFragment.findNavController()
        )
    }

    private fun initFunc() {
        lifecycleScope.launch {
            if (viewModel.getCurrentUser() != null) {
                setSupportActionBar(toolbar)
            } else
                replaceActivity(AuthActivity())
        }
    }

    private fun initFields() {
        FirebaseApp.initializeApp(applicationContext)
        toolbar = binding.toolbar
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}