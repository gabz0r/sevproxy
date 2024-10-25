package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthModel(
    val email: String,
    val password: String
)
