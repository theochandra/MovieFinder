package com.android.moviefinder.di.screen

import com.android.moviefinder.di.scope.PerScreen
import com.android.moviefinder.presentation.MainActivity
import dagger.Subcomponent

@PerScreen
@Subcomponent(modules = [ScreenModule::class])
interface ScreenComponent {

    // todo add all your activities here
    fun inject(mainActivity: MainActivity)

}