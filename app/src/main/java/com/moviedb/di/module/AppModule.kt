package com.moviedb.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @JvmStatic
    @Provides
    @Singleton
    fun provideSharedPrefs(application: Application): SharedPreferences =
        application.getSharedPreferences("moviedb-shared-prefs", Context.MODE_PRIVATE)
}