package com.example.testbckg.presentation.guides

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.testbckg.core.base.BaseFragment
import com.example.testbckg.databinding.FragmentGuidesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GuidesFragment : BaseFragment<FragmentGuidesBinding>() {

    override fun inflaterViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentGuidesBinding.inflate(inflater, container, false)

    override fun initFlow() {

    }

    override fun initListener() {

    }

}