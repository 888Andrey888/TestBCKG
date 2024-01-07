package com.example.testbckg.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.testbckg.R
import com.example.testbckg.core.base.BaseFragment
import com.example.testbckg.databinding.FragmentMainBinding
import com.example.testbckg.presentation.activitys.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel by viewModels<MainFragmentViewModel>()
    private val fragmentsArray = listOf(
        "Guides",
        "Drivers"
    )

    override fun inflaterViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMainBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFields()
    }

    private fun initFields() {
        (activity as MainActivity).title = getString(R.string.home)

        val viewPagerAdapter = ViewPagerAdapter(
            fragmentManager = (activity as MainActivity).supportFragmentManager,
            lifecycle = lifecycle,
            countFragments = fragmentsArray.size
        )
        binding.vpHome.adapter = viewPagerAdapter

        /*TabLayoutMediator(binding.tabHome, binding.vpHome) { tab, position ->
            tab.text = fragmentsArray[position]
        }.attach()*/
    }

    override fun initFlow() {

    }

    override fun initListener() {

    }

}