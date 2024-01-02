package com.example.testbckg.domain.repository

import com.example.testbckg.domain.models.GuideModel
import com.example.testbckg.domain.utils.Resource
import com.example.testbckg.domain.models.TouristModel
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface RepositoryAuth {

    suspend fun getCurrentUser(): FirebaseUser?

    suspend fun firebaseSingUpTourist(touristModel: TouristModel): Flow<Resource<Boolean>>

    suspend fun firebaseSingUpGuide(guideModel: GuideModel): Flow<Resource<Boolean>>

    suspend fun firebaseLogIn(email: String, password: String): Flow<Resource<Boolean>>

    suspend fun firebaseSignOut()

}