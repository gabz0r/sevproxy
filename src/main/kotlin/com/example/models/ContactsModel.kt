package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class ContactModel(
    var customerNumber: String? = null,
    var displayName: String? = null,
    var id: String? = null,
) {
    companion object {
        fun fromNativeModel(nativeModel: NativeContactModel): ContactModel {
            return ContactModel(
                customerNumber = nativeModel.customerNumber,
                displayName = "${nativeModel.name} ${nativeModel.familyname}",
                id = nativeModel.id
            )
        }
    }
}