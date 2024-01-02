package com.example.testbckg.data.utils

import com.example.testbckg.data.firebase.models.GuideModelDto
import com.example.testbckg.data.firebase.models.TouristModelDto
import com.example.testbckg.domain.models.GuideModel
import com.example.testbckg.domain.models.TouristModel

fun TouristModel.mapToDto() = TouristModelDto(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    email = this.email,
    password = this.password
)

fun GuideModel.mapToDto() = GuideModelDto(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    email = this.email,
    password = this.password,
    age = this.age,
    type = this.type,
    experience = this.experience,
    imageUrl = this.imageUrl
)