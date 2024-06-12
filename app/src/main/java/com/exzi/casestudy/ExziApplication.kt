package com.exzi.casestudy

import android.app.Application
import com.exzi.casestudy.di.appModule
import com.exzi.casestudy.features.book.di.bookModuleDi
import com.exzi.casestudy.features.graph.di.graphModuleDi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ExziApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ExziApplication)
            modules(
                appModule,
                graphModuleDi,
                bookModuleDi
            )
        }
    }
}