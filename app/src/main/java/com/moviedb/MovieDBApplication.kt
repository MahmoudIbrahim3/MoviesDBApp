package com.moviedb

import android.app.Activity
import com.moviedb.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MovieDBApplication: DaggerApplication(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityAndroidInjector

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()

        appComponent.inject(this)

        return appComponent
    }
}