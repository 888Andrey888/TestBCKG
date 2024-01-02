package com.example.testbckg.presentation.auth.authguide

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.testbckg.core.base.BaseFragment
import com.example.testbckg.databinding.FragmentAuthGuideBinding

class AuthGuideFragment : BaseFragment<FragmentAuthGuideBinding>() {

    override fun inflaterViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAuthGuideBinding.inflate(inflater, container, false)

    override fun initFlow() {

    }

    override fun initListener() {

    }

}