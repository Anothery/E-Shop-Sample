package com.example.eshopsample.domain.usecase

import com.example.eshopsample.domain.model.Category
import com.example.eshopsample.domain.repository.EShopRepository
import io.reactivex.Flowable
import io.reactivex.Scheduler
import javax.inject.Inject

class UseCaseGetCategories @Inject constructor(
    private val repository: EShopRepository,
    subscribeScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) : UseCase<List<Category>>(subscribeScheduler, postExecutionScheduler) {
    override fun createUseCase(): Flowable<List<Category>> {
        return repository.getCategoriesList().toFlowable()
    }
}