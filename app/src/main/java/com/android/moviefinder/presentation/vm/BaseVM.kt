package com.android.moviefinder.presentation.vm

import com.android.moviefinder.presentation.typesfactory.TypesFactory

abstract class BaseVM {

    abstract fun type(typesFactory: TypesFactory): Int

}