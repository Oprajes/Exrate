package com.example.pewpew.exrate.features.exchangerates.usecase

import com.example.pewpew.exrate.core.di.scope.ApplicationScope
import com.example.pewpew.exrate.core.interactor.UseCaseInterface
import com.example.pewpew.exrate.features.exchangerates.model.Currency
import com.example.pewpew.exrate.features.exchangerates.repository.CurrencyRepository
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ApplicationScope
class UpdateCurrencies
@Inject constructor(private val currencyRepository: CurrencyRepository):
    UseCaseInterface<Unit, UpdateCurrencies.Params>{

    override fun execute(params: Params) {
        Completable.fromAction {
            params.list.forEach { it.position = params.list.indexOf(it) }
            currencyRepository.updateCurrencies(params.list) }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    data class Params(val list: List<Currency>)
}