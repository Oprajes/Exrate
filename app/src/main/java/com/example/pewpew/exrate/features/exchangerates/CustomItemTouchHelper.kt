package com.example.pewpew.exrate.features.exchangerates

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.pewpew.exrate.core.di.scope.FragmentScope
import javax.inject.Inject

@FragmentScope
class CustomItemTouchHelper
@Inject constructor(private val currencySettingsAdapter: CurrencySettingsAdapter): ItemTouchHelper.Callback(){

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
        makeMovementFlags(ItemTouchHelper.UP.or(ItemTouchHelper.DOWN), 0)

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        currencySettingsAdapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

}