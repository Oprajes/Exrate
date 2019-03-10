package com.example.pewpew.exrate.features.exchangerates.usecase

import com.example.pewpew.exrate.core.di.scope.ApplicationScope
import com.example.pewpew.exrate.core.interactor.ObservableUseCase
import com.example.pewpew.exrate.features.exchangerates.model.Currency
import com.example.pewpew.exrate.features.exchangerates.repository.CurrencyRepository
import io.reactivex.Observable
import javax.inject.Inject

@ApplicationScope
class GetCurrencies
@Inject constructor(private val currencyRepository: CurrencyRepository):
    ObservableUseCase<List<Currency>, ObservableUseCase.None>() {

    override fun execute(params: None): Observable<List<Currency>> =
        currencyRepository.getCurrencies(false)
            .map { list -> list.sortedBy { it.position }}
            .toObservable()

}