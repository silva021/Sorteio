package com.silva021.sorteio.app

import android.app.Application
import com.silva021.sorteio.home.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RaffleApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RaffleApp)
            modules(homeModule)
        }
    }
}