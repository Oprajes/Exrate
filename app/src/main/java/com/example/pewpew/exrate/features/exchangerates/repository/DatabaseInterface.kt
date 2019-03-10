package com.example.pewpew.exrate.features.exchangerates.repository

import com.example.pewpew.exrate.features.exchangerates.model.Currency
import io.reactivex.Single

interface DatabaseInterface {
    fun getCurrencies(): Single<List<Currency>>
    fun getCurrencyById(id: Int): Currency?
    fun insertCurrency(currency: Currency)
    fun updateCurrencies(list: List<Currency>)
}