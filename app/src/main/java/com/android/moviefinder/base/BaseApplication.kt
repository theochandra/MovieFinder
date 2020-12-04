package com.android.moviefinder.base

import android.app.Application
import com.android.moviefinder.di.application.ApplicationComponent
import com.android.moviefinder.di.application.ApplicationModule
import com.android.moviefinder.di.application.DaggerApplicationComponent

class BaseApplication : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        inject()
    }

    fun inject() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

}