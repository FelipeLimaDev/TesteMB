package com.fdlr.testemb

import android.app.Application
import com.fdlr.testemb.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TesteMBApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setUpKoin()
    }

    private fun setUpKoin() {
        startKoin {
            androidLogger()
            androidContext(this@TesteMBApplication)
            modules(appModule)
        }
    }
}