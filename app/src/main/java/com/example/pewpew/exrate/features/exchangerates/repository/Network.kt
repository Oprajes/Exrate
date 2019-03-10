package com.example.pewpew.exrate.features.exchangerates.repository

import com.example.pewpew.exrate.core.di.scope.ApplicationScope
import com.example.pewpew.exrate.features.exchangerates.NbrbApi
import com.example.pewpew.exrate.features.exchangerates.model.Currency
import com.example.pewpew.exrate.features.exchangerates.model.CurrencyEntity
import com.example.pewpew.exrate.features.exchangerates.utils.CalendarHelper
import com.example.pewpew.exrate.features.exchangerates.utils.toApiDate
import io.reactivex.Single
import javax.inject.Inject

@ApplicationScope
class Network
@Inject constructor(private val nbrbApi: NbrbApi,
                    private val database: DatabaseInterface): NetworkInterface {

    override fun getCurrencies(): Single<List<Currency>> =
        nbrbApi.getExchangeRates(CalendarHelper.today.toApiDate())
            .flatMap { todayList ->
                nbrbApi.getExchangeRates(CalendarHelper.tomorrow.toApiDate())
                    .flatMap { tomorrowList ->
                        nbrbApi.getExchangeRates(CalendarHelper.yesterday.toApiDate())
                            .map { yesterdayList ->
                                if(tomorrowList.isNotEmpty()) {
                                    CalendarHelper.firstDate = CalendarHelper.today
                                    CalendarHelper.secondDate = CalendarHelper.tomorrow
                                    toCurrencyList(Pair(todayList, tomorrowList))
                                } else {
                                    CalendarHelper.firstDate = CalendarHelper.yesterday
                                    CalendarHelper.secondDate = CalendarHelper.today
                                    toCurrencyList(Pair(yesterdayList, todayList))
                                }
                            }
                    }
            }


    private fun toCurrencyList(pair: Pair<List<CurrencyEntity>, List<CurrencyEntity>>): List<Currency> =
            pair.first.map { currencyEntity ->
                Currency.create(Pair(currencyEntity, pair.second[pair.first.indexOf(currencyEntity)]))
                    .also { currency ->
                        val currencyFromDb = database.getCurrencyById(currency.id)
                        currency.isShown = currencyFromDb?.isShown ?: true
                        currency.position = currencyFromDb?.position ?: pair.first.indexOf(currencyEntity)
                        database.insertCurrency(currency)
                    }
            }

}