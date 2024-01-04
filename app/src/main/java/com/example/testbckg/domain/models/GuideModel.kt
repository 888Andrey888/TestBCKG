package com.example.testbckg.domain.models

import com.example.testbckg.domain.utils.GuideType

data class GuideModel(
    var id: String? = null,
    var firstName: String,
    var lastName: String,
    var email: String,
    var password: String,
    var age: String,
    var type: GuideType,
    var experience: String,
    var imageUrl: String = ""
)
