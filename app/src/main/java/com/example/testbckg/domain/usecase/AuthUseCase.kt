package com.example.testbckg.domain.usecase

import com.example.testbckg.domain.models.GuideModel
import com.example.testbckg.domain.models.TouristModel
import com.example.testbckg.domain.repository.RepositoryAuth
import javax.inject.Inject

class AuthUseCase @Inject constructor(private val repository: RepositoryAuth) {

    suspend fun getCurrentUser() = repository.getCurrentUser()

    suspend fun registerNewTourist(touristModel: TouristModel) =
        repository.firebaseSingUpTourist(touristModel = touristModel)

    suspend fun registerNewGuide(guideModel: GuideModel) =
        repository.firebaseSingUpGuide(guideModel = guideModel)

    suspend fun loginUser(email: String, password: String) =
        repository.firebaseLogIn(email = email, password = password)

    suspend fun signOutUser() = repository.firebaseSignOut()

}