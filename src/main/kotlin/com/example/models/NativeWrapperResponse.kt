package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class NativeWrapperResponse<T>(
    val objects: List<T>
)