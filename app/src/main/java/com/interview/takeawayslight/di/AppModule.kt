package com.interview.takeawayslight.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.interview.takeawayslight.core.SchedulersFactory
import com.interview.takeawayslight.core.SchedulersFactoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesAppContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun providesResources(application: Application): Resources = application.resources

    @Provides
    @Singleton
    fun providesSchedulerFactory(): SchedulersFactory = SchedulersFactoryImpl()
}