package com.psbapp.uidesign.di

import com.psbapp.networking.di.networkModule
import com.psbapp.utils.dispatcher.dispatcherDiModule

val coreDiModule = listOf(
    dispatcherDiModule(),
    networkModule()
)