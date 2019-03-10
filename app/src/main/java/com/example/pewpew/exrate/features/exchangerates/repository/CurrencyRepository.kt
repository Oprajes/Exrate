package com.example.pewpew.exrate.features.exchangerates.repository

import com.example.pewpew.exrate.core.di.scope.ApplicationScope
import com.example.pewpew.exrate.features.exchangerates.model.Currency
import io.reactivex.Single
import javax.inject.Inject

@ApplicationScope
class CurrencyRepository
@Inject constructor(private val network: NetworkInterface,
                    private val database: DatabaseInterface){

    fun getCurrencies(fromNetwork: Boolean): Single<List<Currency>> =
        if(fromNetwork) network.getCurrencies() else database.getCurrencies()

    fun updateCurrencies(list: List<Currency>){
        database.updateCurrencies(list)
    }
}