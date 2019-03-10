package com.example.pewpew.exrate.features.exchangerates.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pewpew.exrate.core.di.scope.ApplicationScope
import com.example.pewpew.exrate.features.exchangerates.model.Currency
import com.example.pewpew.exrate.features.exchangerates.usecase.GetShownCurrencies
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@ApplicationScope
class CurrencyViewModel
@Inject constructor(private val getShownCurrencies: GetShownCurrencies): ViewModel(){

    val currencies: MutableLiveData<List<Currency>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    var isDataLoaded = false
    private var disposable: Disposable? = null

    fun loadData(){
        getCurrencies(true)
    }

    fun getCurrencies(fromNetwork: Boolean = false){
        isLoading.value = true
        disposable = getShownCurrencies(GetShownCurrencies.Params(fromNetwork))
            .subscribe({ list ->
                isLoading.value = false
                currencies.value = list
            }, {
                isLoading.value = false
                errorMessage.value = "Не удалось получить курсы валют"
            }, {
                isDataLoaded = true
            })
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}