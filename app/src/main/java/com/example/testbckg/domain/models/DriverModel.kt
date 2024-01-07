package com.example.testbckg.domain.models

data class DriverModel(
    var id: String? = null,
    var firstName: String,
    var lastName: String,
    var email: String,
    var password: String,
    var age: String,
    var experience: String,
    var imageUrl: String = ""
)
