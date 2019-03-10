package com.example.pewpew.exrate.core.interactor

interface UseCaseInterface<Type, Params> {
    fun execute(params: Params): Type
}