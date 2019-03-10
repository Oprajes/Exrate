package com.example.pewpew.exrate.core.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pewpew.exrate.core.di.scope.ApplicationScope
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider

@ApplicationScope
class ViewModelFactory
@Inject constructor(private val creators: Map<Class<out ViewModel>,
        @JvmSuppressWildcards Provider<ViewModel>>): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?:
        creators.asIterable().firstOrNull{modelClass.isAssignableFrom(it.key)}?.value ?:
        throw IllegalArgumentException("Unknown view model class $modelClass")
        return creator.get() as T
    }
}