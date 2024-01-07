package com.example.testbckg.presentation.drivers

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.testbckg.core.base.BaseFragment
import com.example.testbckg.databinding.FragmentDriversBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DriversFragment : BaseFragment<FragmentDriversBinding>() {

    override fun inflaterViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDriversBinding.inflate(inflater, container, false)

    override fun initFlow() {

    }

    override fun initListener() {

    }

}