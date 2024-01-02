package com.example.testbckg.data.firebase.storage

import android.util.Log
import com.example.testbckg.data.firebase.models.GuideModelDto
import com.example.testbckg.data.firebase.models.TouristModelDto
import com.example.testbckg.data.utils.Constants.GUIDES
import com.example.testbckg.data.utils.Constants.TOURISTS
import com.example.testbckg.domain.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthStorageImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) : FirebaseAuthStorage {

    private val TAG = "ololo"

    override suspend fun getCurrentUser() = firebaseAuth.currentUser

    override suspend fun firebaseSingUpTourist(touristModelDto: TouristModelDto) = flow {
        emit(Resource.Loading())

        try {
            val resultAuth = firebaseAuth.createUserWithEmailAndPassword(
                touristModelDto.email,
                touristModelDto.password
            ).await()

            if (resultAuth != null) {
                val resultFirestore = if (firebaseAuth.currentUser != null) {
                    touristModelDto.id = firebaseAuth.currentUser!!.uid
                    firebaseFirestore.collection(TOURISTS).document(firebaseAuth.currentUser!!.uid)
                        .set(touristModelDto).await()
                    true
                } else
                    false
                if (resultFirestore)
                    emit(Resource.Success(true))
                else
                    emit(Resource.Error("Oh, something went wrong"))
            }else
                emit(Resource.Error("Oh, something went wrong"))
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    message = e.localizedMessage ?: "Oh, something went wrong"
                )
            )
        }
    }

    override suspend fun firebaseSingUpGuide(guideModelDto: GuideModelDto) = flow {
        emit(Resource.Loading())

        try {
            val resultAuth = firebaseAuth.createUserWithEmailAndPassword(
                guideModelDto.email,
                guideModelDto.password
            ).await()

            if (resultAuth != null) {
                val resultFirestore = if (firebaseAuth.currentUser != null) {
                    guideModelDto.id = firebaseAuth.currentUser!!.uid
                    firebaseFirestore.collection(TOURISTS).document(firebaseAuth.currentUser!!.uid)
                        .set(guideModelDto).await()
                    true
                } else
                    false
                if (resultFirestore)
                    emit(Resource.Success(true))
                else
                    emit(Resource.Error("Oh, something went wrong"))
            }else
                emit(Resource.Error("Oh, something went wrong"))
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    message = e.localizedMessage ?: "Oh, something went wrong"
                )
            )
        }
    }

    override suspend fun firebaseLogIn(email: String, password: String) = flow {
        emit(Resource.Loading())

        try {
            val resultAuth = firebaseAuth.signInWithEmailAndPassword(email, password).await()

            if (resultAuth != null)
                emit(Resource.Success(true))
            else
                emit(Resource.Error("Oh, something went wrong"))
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    message = e.localizedMessage ?: "Oh, something went wrong"
                )
            )
        }
    }

    override suspend fun firebaseSignOut() {
        firebaseAuth.signOut()
    }
}