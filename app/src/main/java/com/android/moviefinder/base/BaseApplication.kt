package com.android.moviefinder.base

import android.app.Application
import com.android.moviefinder.di.Injector
import com.android.moviefinder.di.application.AppComponent
import com.android.moviefinder.di.application.AppModule
import com.android.moviefinder.di.application.DaggerAppComponent
import com.android.moviefinder.di.movie.MovieSubComponent

class BaseApplication : Application(), Injector {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .build()

//        component = DaggerApplicationComponent.builder()
//            .applicationModule(AppModule(applicationContext))
//            .networkModule(NetModule())
//            .build()

//        inject()
    }

    override fun createMovieSubComponent(): MovieSubComponent {
        return appComponent.movieSubComponent().create()
    }

//    fun inject() {
//        component = DaggerApplicationComponent.builder()
//                .applicationModule(ApplicationModule(this)).build()
//        component.inject(this)
//    }

}