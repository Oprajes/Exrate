package com.example.pewpew.exrate.core.interactor

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class ObservableUseCase<Type, Params>: UseCaseInterface<Observable<Type>, Params> {

    abstract override fun execute(params: Params): Observable<Type>

    operator fun invoke(params: Params): Observable<Type> =
        execute(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    class None
}