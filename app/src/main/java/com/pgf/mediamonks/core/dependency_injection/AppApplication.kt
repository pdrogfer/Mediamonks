package com.pgf.mediamonks.core.dependency_injection

import android.app.Application

class AppApplication : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(
                ApplicationModule(
                    this
                )
            )
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}