package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class NativeContactModel(
    var customerNumber: String? = null,
    var name: String? = null,
    var name2: String? = null,
    var surename: String? = null,
    var taxNumber: String? = null,
    var taxOffice: String? = null,
    var academicTitle: String? = null,
    var vatNumber: String? = null,
    var familyname: String? = null,
    var gender: String? = null,
    var bankAccount: String? = null,
    var bankNumber: String? = null,
    var exemptVat: String? = null,
    var defaultDiscountAmount: Float? = null,
    var defaultDiscountPercentage: String? = null,
    var defaultCashbackPercent: Float? = null,
    var defaultCashbackTime: Int? = null,
    var defaultTimeToPay: Int? = null,
    var description: String? = null,
    var id: String? = null,
)