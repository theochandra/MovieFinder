package com.android.moviefinder.di.application

import com.android.moviefinder.base.BaseApplication
import com.android.moviefinder.di.screen.ScreenComponent
import com.android.moviefinder.di.screen.ScreenModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    RepositoryModule::class,
    NetworkModule::class
])
interface ApplicationComponent {

    fun inject(activity: BaseApplication)

    fun plus(screenModule: ScreenModule): ScreenComponent

}