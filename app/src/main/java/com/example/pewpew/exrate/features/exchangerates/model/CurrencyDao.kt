package com.example.pewpew.exrate.features.exchangerates.model

import androidx.room.*
import io.reactivex.Single

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM currency")
    fun getCurrencies(): Single<List<Currency>>

    @Query("SELECT * FROM currency WHERE id = :id")
    fun getCurrencyById(id: Int): Currency?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currency: Currency)

    @Update
    fun update(list: List<Currency>)
}