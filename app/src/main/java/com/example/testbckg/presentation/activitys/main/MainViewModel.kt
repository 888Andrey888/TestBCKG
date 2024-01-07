package com.example.testbckg.presentation.activitys.main

import androidx.lifecycle.ViewModel
import com.example.testbckg.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val authUseCase: AuthUseCase) : ViewModel() {

    suspend fun getCurrentUser() = authUseCase.getCurrentUser()

    suspend fun signOutUser() = authUseCase.signOutUser()

}