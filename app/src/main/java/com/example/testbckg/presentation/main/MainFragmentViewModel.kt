package com.example.testbckg.presentation.main

import androidx.lifecycle.ViewModel
import com.example.testbckg.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(private val authUseCase: AuthUseCase) :
    ViewModel() {

}