package com.example.testbckg.di

import com.example.testbckg.data.firebase.storage.FirebaseAuthStorage
import com.example.testbckg.data.firebase.storage.FirebaseAuthStorageImpl
import com.example.testbckg.data.repository.RepositoryAuthImpl
import com.example.testbckg.domain.repository.RepositoryAuth
import com.example.testbckg.domain.usecase.AuthUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseAuthStorage(
        firebaseAuth: FirebaseAuth,
        firebaseFirestore: FirebaseFirestore
    ): FirebaseAuthStorage {
        return FirebaseAuthStorageImpl(
            firebaseAuth = firebaseAuth,
            firebaseFirestore = firebaseFirestore
        )
    }

    @Provides
    @Singleton
    fun provideRepositoryAuth(firebaseAuthStorage: FirebaseAuthStorage): RepositoryAuth {
        return RepositoryAuthImpl(firebaseAuthStorage)
    }

    @Singleton
    @Provides
    fun provideAuthUseCase(repositoryAuth: RepositoryAuth) = AuthUseCase(repositoryAuth)

}