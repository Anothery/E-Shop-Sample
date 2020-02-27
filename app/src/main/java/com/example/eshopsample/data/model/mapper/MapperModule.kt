package com.example.eshopsample.data.model.mapper

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapperModule {
    @Singleton
    @Provides
    fun provideCategoryEntityToDomainMapper() = CategoryDataToDomainMapper()
}