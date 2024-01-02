package com.example.testbckg.presentation.login

import com.example.testbckg.core.base.BaseViewModel
import com.example.testbckg.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCase: AuthUseCase) :
    BaseViewModel<Boolean>() {

    suspend fun loginUser(email: String, password: String) = doOperation(
        operation = { authUseCase.loginUser(email = email, password = password) }
    )
}