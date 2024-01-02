package com.example.testbckg.data.repository

import com.example.testbckg.data.firebase.storage.FirebaseAuthStorage
import com.example.testbckg.data.utils.mapToDto
import com.example.testbckg.domain.models.GuideModel
import com.example.testbckg.domain.models.TouristModel
import com.example.testbckg.domain.repository.RepositoryAuth
import javax.inject.Inject

class RepositoryAuthImpl @Inject constructor(private val firebaseAuthStorage: FirebaseAuthStorage) :
    RepositoryAuth {

    override suspend fun getCurrentUser() = firebaseAuthStorage.getCurrentUser()

    override suspend fun firebaseSingUpTourist(touristModel: TouristModel) =
        firebaseAuthStorage.firebaseSingUpTourist(touristModelDto = touristModel.mapToDto())

    override suspend fun firebaseSingUpGuide(guideModel: GuideModel) =
        firebaseAuthStorage.firebaseSingUpGuide(guideModelDto = guideModel.mapToDto())

    override suspend fun firebaseLogIn(email: String, password: String) =
        firebaseAuthStorage.firebaseLogIn(email, password)

    override suspend fun firebaseSignOut() = firebaseAuthStorage.firebaseSignOut()

}