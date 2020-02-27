package com.example.eshopsample.data

import com.example.eshopsample.data.model.mapper.MapperModule
import com.example.eshopsample.data.repository.EShopDataRepository
import com.example.eshopsample.data.source.network.NetworkModule
import com.example.eshopsample.domain.repository.EShopRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, MapperModule::class])
class DataModule {
    @Singleton
    @Provides
    fun provideEShopRepository(eShopDataRepository: EShopDataRepository): EShopRepository =
        eShopDataRepository
}