package com.example.testbckg.presentation.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.testbckg.core.base.BaseFragment
import com.example.testbckg.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>() {

    override fun inflaterViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentFavoritesBinding.inflate(inflater, container, false)

    override fun initFlow() {

    }

    override fun initListener() {

    }

}