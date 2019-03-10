package com.example.pewpew.exrate.features.exchangerates.usecase

import com.example.pewpew.exrate.core.di.scope.ApplicationScope
import com.example.pewpew.exrate.core.interactor.ObservableUseCase
import com.example.pewpew.exrate.features.exchangerates.model.Currency
import com.example.pewpew.exrate.features.exchangerates.repository.CurrencyRepository
import io.reactivex.Observable
import javax.inject.Inject

@ApplicationScope
class GetShownCurrencies
@Inject constructor(private val currencyRepository: CurrencyRepository):
ObservableUseCase<List<Currency>, GetShownCurrencies.Params>(){

    override fun execute(params: Params): Observable<List<Currency>> =
        currencyRepository.getCurrencies(params.fromNetwork)
            .map { list -> list.filter { it.isShown } }
            .map { list -> list.sortedBy { it.position } }
            .toObservable()


    data class Params(val fromNetwork: Boolean)
}