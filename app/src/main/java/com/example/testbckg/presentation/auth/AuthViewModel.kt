package com.example.testbckg.presentation.auth

import com.example.testbckg.core.base.BaseViewModel
import com.example.testbckg.domain.models.GuideModel
import com.example.testbckg.domain.models.TouristModel
import com.example.testbckg.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authUseCase: AuthUseCase) :
    BaseViewModel<Boolean>() {

    suspend fun registerNewTourist(touristModel: TouristModel) = doOperation(
        operation = { authUseCase.registerNewTourist(touristModel) }
    )

}