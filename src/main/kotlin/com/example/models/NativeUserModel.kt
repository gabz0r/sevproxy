package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class NativeUserModel(
    val userId: String,
    val clientId: String,
    val apiToken: String,
    val cft: String
)
