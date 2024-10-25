package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class NativeAuthResponseModel(
    val user: NativeUserModel,
    val status: String
)