package com.example.pewpew.exrate.features.exchangerates.view


import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pewpew.exrate.R
import com.example.pewpew.exrate.core.FragmentStack
import com.example.pewpew.exrate.core.MainActivity
import com.example.pewpew.exrate.core.di.viewmodel.ViewModelFactory
import com.example.pewpew.exrate.features.exchangerates.CurrencyAdapter
import com.example.pewpew.exrate.features.exchangerates.model.Currency
import com.example.pewpew.exrate.features.exchangerates.utils.CalendarHelper
import com.example.pewpew.exrate.features.exchangerates.utils.toViewDate
import com.example.pewpew.exrate.features.exchangerates.viewmodel.CurrencyViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class ListFragment : Fragment() {
    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var currencyAdapter: CurrencyAdapter

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
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        if(savedInstanceState == null)
            viewModel.loadData()
        else
            viewModel.getShownCurrencies()
        viewModel.shownCurrencies.observe(this, Observer(this::showData))
        viewModel.isLoading.observe(this, Observer(this::showLoading))
        viewModel.errorMessage.observe(this, Observer(this::showError))
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.fragment_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.settings -> fragmentStack.push(SettingsFragment())
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViews(){
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        recyclerView.adapter = currencyAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun showData(collection: List<Currency>){
        currencyAdapter.collection = collection
        firstDate.text = CalendarHelper.firstDate?.toViewDate()
        secondDate.text = CalendarHelper.secondDate?.toViewDate()
    }

    private fun showLoading(isLoading: Boolean){
        progressBar.visibility = if(isLoading) View.VISIBLE else View.INVISIBLE
    }

    private fun showError(errorMessage: String){
        Snackbar.make(layout_list, errorMessage, Snackbar.LENGTH_LONG).show()
        setHasOptionsMenu(false)
    }
}
