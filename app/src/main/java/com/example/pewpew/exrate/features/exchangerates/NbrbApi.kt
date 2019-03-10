package com.example.pewpew.exrate.features.exchangerates

import com.example.pewpew.exrate.features.exchangerates.model.CurrencyEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

//http://www.nbrb.by/API/ExRates/Rates?onDate=2019-3-7&Periodicity=0
interface NbrbApi {
    @GET("API/ExRates/Rates")
    fun getExchangeRates(@Query("onDate") onDate: String,
                         @Query("Periodicity") periodicity: Int = 0): Single<List<CurrencyEntity>>
}
