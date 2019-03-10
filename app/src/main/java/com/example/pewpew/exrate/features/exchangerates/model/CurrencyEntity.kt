package com.example.pewpew.exrate.features.exchangerates.model

data class CurrencyEntity(
    val Cur_Abbreviation: String,
    val Cur_ID: Int,
    val Cur_Name: String,
    val Cur_OfficialRate: Double,
    val Cur_Scale: Int,
    val Date: String
)
//    fun toCurrency() = Currency(Cur_Abbreviation, Cur_Name, Cur_OfficialRate, Cur_Scale)
//}