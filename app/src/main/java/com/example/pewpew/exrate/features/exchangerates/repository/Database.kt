package com.example.pewpew.exrate.features.exchangerates.repository

import com.example.pewpew.exrate.core.di.scope.ApplicationScope
import com.example.pewpew.exrate.features.exchangerates.model.Currency
import com.example.pewpew.exrate.features.exchangerates.model.CurrencyDao
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ApplicationScope
class Database
@Inject constructor(private val currencyDao: CurrencyDao): DatabaseInterface{

    override fun getCurrencies(): Single<List<Currency>> = currencyDao.getCurrencies()

    override fun getCurrencyById(id: Int): Currency? = currencyDao.getCurrencyById(id)

    override fun insertCurrency(currency: Currency) {
        Completable.fromAction { currencyDao.insert(currency) }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun updateCurrencies(list: List<Currency>) {
        currencyDao.update(list)
    }
}