package com.example.eshopsample.di

import com.example.eshopsample.ui.main.MainActivity
import com.example.eshopsample.ui.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindMainActivity(): MainActivity
}