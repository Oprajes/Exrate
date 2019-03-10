package com.example.pewpew.exrate.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pewpew.exrate.features.exchangerates.model.Currency
import com.example.pewpew.exrate.features.exchangerates.model.CurrencyDao

@Database(entities = [Currency::class], version = 1)
abstract class CurrencyDatabase: RoomDatabase() {
    companion object {
        const val DB_NAME = "exrate.db"
    }
    abstract fun getCurrencyDao(): CurrencyDao
}