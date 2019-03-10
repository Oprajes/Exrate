package com.example.pewpew.exrate.core

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.pewpew.exrate.R
import com.example.pewpew.exrate.features.exchangerates.view.ListFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity(), FragmentStack {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, ListFragment())
                .commit()
    }

    override fun push(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun pop() {
        supportFragmentManager.popBackStack()
    }
}
