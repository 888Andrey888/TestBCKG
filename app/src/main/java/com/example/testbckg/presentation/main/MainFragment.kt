package com.example.testbckg.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.testbckg.R
import com.example.testbckg.core.base.BaseFragment
import com.example.testbckg.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel by viewModels<MainFragmentViewModel>()

    override fun inflaterViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMainBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkUser()
    }

    override fun initFlow() {

    }

    override fun initListener() {
        binding.btnSignOut.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.signOutUser()
                checkUser()
            }
        }
    }

    private fun checkUser() {
        lifecycleScope.launch {
            if (viewModel.getCurrentUser() == null)
                findNavController().navigate(R.id.logInFragment)
        }
    }

}