package com.example.eshopsample.di

import com.example.eshopsample.ui.detail.DetailActivity
import com.example.eshopsample.ui.detail.DetailModule
import com.example.eshopsample.ui.main.MainActivity
import com.example.eshopsample.ui.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [DetailModule::class])
    abstract fun bindDetailActivity(): DetailActivity
}