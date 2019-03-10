package com.example.pewpew.exrate.core

import androidx.fragment.app.Fragment

interface FragmentStack {
    fun push(fragment: Fragment)
    fun pop()
}