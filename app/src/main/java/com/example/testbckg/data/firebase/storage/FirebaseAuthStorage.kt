package com.example.testbckg.data.firebase.storage

import com.example.testbckg.data.firebase.models.GuideModelDto
import com.example.testbckg.data.firebase.models.TouristModelDto
import com.example.testbckg.domain.utils.Resource
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthStorage {

    suspend fun getCurrentUser(): FirebaseUser?

    suspend fun firebaseSingUpTourist(touristModelDto: TouristModelDto): Flow<Resource<Boolean>>

    suspend fun firebaseSingUpGuide(guideModelDto: GuideModelDto): Flow<Resource<Boolean>>

    suspend fun firebaseLogIn(email: String, password: String): Flow<Resource<Boolean>>

    suspend fun firebaseSignOut()

}