package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val token: String
)