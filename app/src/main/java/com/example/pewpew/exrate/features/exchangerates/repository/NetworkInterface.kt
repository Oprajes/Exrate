package com.example.pewpew.exrate.features.exchangerates.repository

import com.example.pewpew.exrate.features.exchangerates.model.Currency
import io.reactivex.Single

interface NetworkInterface {
    fun getCurrencies(): Single<List<Currency>>
}