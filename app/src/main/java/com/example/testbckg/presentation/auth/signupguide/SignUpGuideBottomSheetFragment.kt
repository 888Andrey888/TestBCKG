package com.example.testbckg.presentation.auth.signupguide

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.testbckg.core.base.BaseBottomSheetFragment
import com.example.testbckg.databinding.FragmentSignUpGuideBottomSheetBinding

class SignUpGuideBottomSheetFragment :
    BaseBottomSheetFragment<FragmentSignUpGuideBottomSheetBinding>() {

    override fun inflaterViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSignUpGuideBottomSheetBinding.inflate(inflater, container, false)

    override fun initListener() {

    }

}