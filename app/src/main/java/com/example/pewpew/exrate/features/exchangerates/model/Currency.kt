package com.example.pewpew.exrate.features.exchangerates.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
data class Currency(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "abbreviation") val abbreviation: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "firstOfficialRate") val firstOfficialRate: Double,
    @ColumnInfo(name = "secondOfficialRate") val secondOfficialRate: Double,
    @ColumnInfo(name = "scale") val scale: Int,
    @ColumnInfo(name = "isShown") var isShown: Boolean = true,
    @ColumnInfo(name = "position") var position: Int = 0
){
    companion object {
        fun create(pair: Pair<CurrencyEntity, CurrencyEntity>) =
            Currency(pair.first.Cur_ID,
                pair.first.Cur_Abbreviation,
                pair.first.Cur_Name,
                pair.first.Cur_OfficialRate,
                pair.second.Cur_OfficialRate,
                pair.first.Cur_Scale)
    }
}