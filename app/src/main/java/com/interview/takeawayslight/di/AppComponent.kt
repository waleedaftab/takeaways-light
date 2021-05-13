package com.interview.takeawayslight.di

import android.app.Application
import com.interview.data.di.DataModule
import com.interview.domain.di.DomainModule
import com.interview.takeawayslight.core.TakeawaysLightApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidInjectionModule::class,
        DataModule::class,
        DomainModule::class,
        ActivityBuilderModule::class
    ]
)
interface AppComponent : AndroidInjector<TakeawaysLightApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}