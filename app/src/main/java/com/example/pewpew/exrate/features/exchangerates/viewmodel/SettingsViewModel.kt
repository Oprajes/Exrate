package com.example.pewpew.exrate.features.exchangerates.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pewpew.exrate.core.di.scope.ApplicationScope
import com.example.pewpew.exrate.core.interactor.ObservableUseCase
import com.example.pewpew.exrate.features.exchangerates.model.Currency
import com.example.pewpew.exrate.features.exchangerates.usecase.GetCurrencies
import com.example.pewpew.exrate.features.exchangerates.usecase.UpdateCurrencies
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@ApplicationScope
class SettingsViewModel
@Inject constructor(private val getCurrencies: GetCurrencies,
                    private val updateCurrencies: UpdateCurrencies): ViewModel(){

    val currencies: MutableLiveData<List<Currency>> = MutableLiveData()
    private var disposable: Disposable? = null

    fun getCurrencies(){
        disposable = getCurrencies(ObservableUseCase.None())
            .subscribe({list ->
                currencies.value = list
            }, {
                Log.d(javaClass.simpleName, "Database isn't responding")
            })
    }

    fun updateData(list: List<Currency>){
        updateCurrencies.execute(UpdateCurrencies.Params(list))
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}