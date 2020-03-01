package com.example.eshopsample.domain

import com.example.eshopsample.domain.repository.EShopRepository
import com.example.eshopsample.domain.usecase.UseCaseGetCategoryWithProducts
import com.example.eshopsample.domain.usecase.UseCaseGetProductDetails
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
    fun provideIoScheduler(): Scheduler = Schedulers.io()

    @Provides
    @Singleton
    @Named("Main")
    fun provideMainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    fun provideUseCaseGetCategoryWithProducts(
        repository: EShopRepository,
        @Named("IO") subsScheduler: Scheduler,
        @Named("Main") postExecScheduler: Scheduler
    ) = UseCaseGetCategoryWithProducts(repository, subsScheduler, postExecScheduler)

    @Provides
    @Singleton
    fun provideUseCaseGetProductDetails(
        repository: EShopRepository,
        @Named("IO") subsScheduler: Scheduler,
        @Named("Main") postExecScheduler: Scheduler
    ) = UseCaseGetProductDetails(repository, subsScheduler, postExecScheduler)

}