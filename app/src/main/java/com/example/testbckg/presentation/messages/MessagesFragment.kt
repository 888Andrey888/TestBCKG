package com.example.testbckg.presentation.messages

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.testbckg.R
import com.example.testbckg.core.base.BaseFragment
import com.example.testbckg.databinding.FragmentMessagesBinding
import com.example.testbckg.presentation.activitys.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessagesFragment : BaseFragment<FragmentMessagesBinding>() {

    override fun inflaterViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMessagesBinding.inflate(inflater, container, false)

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).title = getString(R.string.messages)
    }

    override fun initFlow() {

    }

    override fun initListener() {

    }

}