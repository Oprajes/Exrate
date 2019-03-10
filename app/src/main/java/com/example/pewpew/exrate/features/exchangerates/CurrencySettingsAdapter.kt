package com.example.pewpew.exrate.features.exchangerates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pewpew.exrate.R
import com.example.pewpew.exrate.core.di.scope.FragmentScope
import com.example.pewpew.exrate.features.exchangerates.model.Currency
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_currency_settings.*
import java.util.*
import javax.inject.Inject

@FragmentScope
class CurrencySettingsAdapter
@Inject constructor(): RecyclerView.Adapter<CurrencySettingsAdapter.ViewHolder>(){

    var collection: List<Currency> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencySettingsAdapter.ViewHolder =
        ViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_currency_settings, parent, false))

    override fun getItemCount() = collection.size

    override fun onBindViewHolder(holder: CurrencySettingsAdapter.ViewHolder, position: Int) {
        holder.bind(collection[position])
    }

    fun onItemMove(fromPosition: Int, toPosition: Int){
        if(fromPosition < toPosition)
            for(i in fromPosition until toPosition)
                Collections.swap(collection, i, i+1)
        else
            for(i in fromPosition downTo (toPosition+1))
                Collections.swap(collection, i, i-1)
        notifyItemMoved(fromPosition, toPosition)
    }

    class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer{
        fun bind(currency: Currency){
            abbreviation.text = currency.abbreviation
            name.text = containerView.resources.getString(R.string.currency_name, currency.scale, currency.name)
            favouriteSwitch.isChecked = currency.isShown
            favouriteSwitch.setOnClickListener { currency.isShown = !currency.isShown }
        }
    }
}