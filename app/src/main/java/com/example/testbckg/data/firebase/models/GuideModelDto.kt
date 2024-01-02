package com.example.testbckg.data.firebase.models

import com.example.testbckg.domain.utils.GuideType

data class GuideModelDto(
    var id: String? = null,
    var firstName: String,
    var lastName: String,
    var email: String,
    var password: String,
    var age: Long,
    var type: GuideType,
    var experience: Int,
    var imageUrl: String
)
