package com.example.eshopsample.ui.detail

import dagger.Module
import dagger.Provides

@Module
class DetailModule {
    @Provides
    fun provideDetailPresenter(detailPresenter: DetailPresenter): DetailContract.Presenter =
        detailPresenter
}