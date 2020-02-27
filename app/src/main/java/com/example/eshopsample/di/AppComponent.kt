package com.example.eshopsample.di

import android.app.Application
import com.example.eshopsample.EShopApp
import com.example.eshopsample.data.DataModule
import com.example.eshopsample.domain.UseCaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, DataModule::class, ActivityBindingModule::class, UseCaseModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: EShopApp)
}