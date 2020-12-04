package com.android.moviefinder.di.screen

import com.android.moviefinder.di.scope.PerScreen
import com.android.moviefinder.base.BaseActivity
import dagger.Module
import dagger.Provides

@Module
class ScreenModule(private val activity: BaseActivity) {

    @PerScreen
    @Provides
    fun providesActivity(): BaseActivity {
        return activity
    }

}