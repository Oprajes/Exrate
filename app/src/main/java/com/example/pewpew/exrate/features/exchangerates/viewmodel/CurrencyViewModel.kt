package com.example.pewpew.exrate.features.exchangerates.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pewpew.exrate.core.di.scope.ApplicationScope
import com.example.pewpew.exrate.core.interactor.ObservableUseCase
import com.example.pewpew.exrate.features.exchangerates.model.Currency
import com.example.pewpew.exrate.features.exchangerates.usecase.GetCurrencies
import com.example.pewpew.exrate.features.exchangerates.usecase.GetShownCurrencies
import com.example.pewpew.exrate.features.exchangerates.usecase.UpdateCurrencies
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@ApplicationScope
class CurrencyViewModel
@Inject constructor(private val getCurrencies: GetCurrencies,
                    private val getShownCurrencies: GetShownCurrencies,
                    private val updateCurrencies: UpdateCurrencies): ViewModel(){

    var currencies: MutableLiveData<List<Currency>> = MutableLiveData()
    var shownCurrencies: MutableLiveData<List<Currency>> = MutableLiveData()
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()
    var errorMessage: MutableLiveData<String> = MutableLiveData()
    private val compositeDisposable = CompositeDisposable()

    fun getCurrencies(){
        getCurrencies(ObservableUseCase.None())
            .subscribe({list ->
                currencies.value = list
            }, {
                Log.d("fuck", "fuck")
            }).also { compositeDisposable.add(it) }
    }

    fun loadData(){
        getShownCurrencies(true)
    }

    fun getShownCurrencies(fromNetwork: Boolean = false){
        isLoading.value = true
        getShownCurrencies(GetShownCurrencies.Params(fromNetwork))
            .subscribe({ list ->
                isLoading.value = false
                shownCurrencies.value = list
            }, {
                isLoading.value = false
                errorMessage.value = "Не удалось получить курсы валют"
            }).also { compositeDisposable.add(it) }
    }

    fun updateData(list: List<Currency>){
        updateCurrencies.execute(UpdateCurrencies.Params(list))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}