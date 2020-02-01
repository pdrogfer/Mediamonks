package com.pgf.mediamonks.core.dependency_injection

import com.pgf.mediamonks.core.dependency_injection.viewmodel.ViewModelModule
import com.pgf.mediamonks.ui.main.MainActivity
import com.pgf.mediamonks.ui.main.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {

    // app
    fun inject(application: AppApplication)

    // activities
    fun inject(mainActivity: MainActivity)

    // fragments
    fun inject(mainFragment: MainFragment)

}