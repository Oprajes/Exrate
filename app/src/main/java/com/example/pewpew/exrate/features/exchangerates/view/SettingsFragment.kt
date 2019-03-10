package com.example.pewpew.exrate.features.exchangerates.view


import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pewpew.exrate.R
import com.example.pewpew.exrate.core.FragmentStack
import com.example.pewpew.exrate.core.MainActivity
import com.example.pewpew.exrate.core.di.viewmodel.ViewModelFactory
import com.example.pewpew.exrate.features.exchangerates.CurrencySettingsAdapter
import com.example.pewpew.exrate.features.exchangerates.model.Currency
import com.example.pewpew.exrate.features.exchangerates.viewmodel.CurrencyViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject

class SettingsFragment : Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var currencySettingsAdapter: CurrencySettingsAdapter
    @Inject lateinit var itemTouchHelper: ItemTouchHelper

    private val fragmentStack: FragmentStack by lazy { activity as MainActivity }
    private val viewModel: CurrencyViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(CurrencyViewModel::class.java)
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        viewModel.getCurrencies()
        viewModel.currencies.observe(this, Observer(this::showSettings))
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.fragment_settings_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.saveSettings -> saveSettings()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViews(){
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener { fragmentStack.pop() }
        recyclerView.adapter = currencySettingsAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun showSettings(collection: List<Currency>){
        currencySettingsAdapter.collection = collection
    }

    private fun saveSettings(){
        viewModel.updateData(currencySettingsAdapter.collection)
        fragmentStack.pop()
    }

}
