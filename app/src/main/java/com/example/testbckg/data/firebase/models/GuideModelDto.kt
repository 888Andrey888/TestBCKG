package com.example.testbckg.data.firebase.models

data class GuideModelDto(
    var id: String? = null,
    var firstName: String,
    var lastName: String,
    var email: String,
    var password: String,
    var age: String,
    var type: String,
    var experience: String,
    var imageUrl: String = ""
)
