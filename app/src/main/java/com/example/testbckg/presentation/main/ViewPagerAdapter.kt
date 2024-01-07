package com.example.testbckg.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.testbckg.presentation.drivers.DriversFragment
import com.example.testbckg.presentation.guides.GuidesFragment

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val countFragments: Int
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount() = countFragments

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> GuidesFragment()
            else -> DriversFragment()
        }
    }

}