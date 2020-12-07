package com.android.moviefinder.di.application

import com.android.moviefinder.di.movie.MovieSubComponent
//import com.android.moviefinder.di.ViewModelFactory
//import com.android.moviefinder.di.screen.ScreenComponent
//import com.android.moviefinder.di.screen.ScreenModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    RepositoryModule::class,
    NetModule::class
])
interface AppComponent {

    fun movieSubComponent(): MovieSubComponent.Factory

//    fun inject(activity: BaseApplication)
//
//    fun plus(screenModule: ScreenModule): ScreenComponent
//
//    fun mainViewModelFactory(): ViewModelFactory<MainViewModel>

}