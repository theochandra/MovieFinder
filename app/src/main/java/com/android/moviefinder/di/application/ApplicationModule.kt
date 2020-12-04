package com.android.moviefinder.di.application

import com.android.moviefinder.base.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: BaseApplication) {

    @Provides
    @Singleton
    fun provideApplication(): BaseApplication {
        return application
    }

}