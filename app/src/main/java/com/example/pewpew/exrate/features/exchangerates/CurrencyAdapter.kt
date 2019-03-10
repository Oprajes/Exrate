package com.example.pewpew.exrate.features.exchangerates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pewpew.exrate.R
import com.example.pewpew.exrate.core.di.scope.FragmentScope
import com.example.pewpew.exrate.features.exchangerates.model.Currency
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_currency.*
import javax.inject.Inject

@FragmentScope
class CurrencyAdapter
@Inject constructor(): RecyclerView.Adapter<CurrencyAdapter.ViewHolder>(){

    var collection: List<Currency> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyAdapter.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false))

    override fun getItemCount(): Int = collection.size

    override fun onBindViewHolder(holder: CurrencyAdapter.ViewHolder, position: Int) {
        holder.bind(collection[position])
    }

    class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer{
        fun bind(currency: Currency){
            abbreviation.text = currency.abbreviation
            name.text = containerView.resources.getString(R.string.currency_name, currency.scale, currency.name)
            firstRate.text = containerView.resources.getString(R.string.currency_rate, currency.firstOfficialRate)
            secondRate.text = containerView.resources.getString(R.string.currency_rate, currency.secondOfficialRate)
        }
    }
}