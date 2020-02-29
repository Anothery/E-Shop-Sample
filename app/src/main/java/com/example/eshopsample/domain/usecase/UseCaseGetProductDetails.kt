package com.example.eshopsample.domain.usecase

import com.example.eshopsample.domain.model.ProductDetail
import com.example.eshopsample.domain.repository.EShopRepository
import io.reactivex.Flowable
import io.reactivex.Scheduler
import javax.inject.Inject

class UseCaseGetProductDetails @Inject constructor(
    private val repository: EShopRepository,
    subscribeScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) : UseCase<ProductDetail>(subscribeScheduler, postExecutionScheduler) {
    private var productId = 0

    fun setProductId(id: Int) {
        productId = id
    }

    override fun createUseCase(): Flowable<ProductDetail> {
        return repository.getProductDetails(productId).toFlowable()
    }
}


