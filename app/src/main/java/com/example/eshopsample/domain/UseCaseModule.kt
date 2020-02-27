package com.example.eshopsample.domain

import com.example.eshopsample.domain.repository.EShopRepository
import com.example.eshopsample.domain.usecase.UseCaseGetCategories
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Provides
    @Singleton
    @Named("IO")
    fun provideIoScheduler() = Schedulers.io()

    @Provides
    @Singleton
    @Named("Main")
    fun provideMainThreadScheduler() = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    fun provideUseCaseGetCategories(
        repository: EShopRepository,
        @Named("IO") subsScheduler: Scheduler,
        @Named("Main") postExecScheduler: Scheduler
    ): UseCaseGetCategories = UseCaseGetCategories(repository, subsScheduler, postExecScheduler)

}