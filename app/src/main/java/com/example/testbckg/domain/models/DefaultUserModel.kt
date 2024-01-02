package com.example.testbckg.domain.models

import java.io.Serializable

data class DefaultUserModel(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
): Serializable