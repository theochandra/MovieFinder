package com.android.moviefinder.presentation

import android.os.Bundle
import com.android.moviefinder.R
import com.android.moviefinder.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}