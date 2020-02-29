package com.example.eshopsample.domain.usecase

import com.example.eshopsample.domain.model.CategoryWithProducts
import com.example.eshopsample.domain.repository.EShopRepository
import io.reactivex.Flowable
import io.reactivex.Scheduler
import javax.inject.Inject

class UseCaseGetCategoryWithProducts @Inject constructor(
    private val repository: EShopRepository,
    subscribeScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) : UseCase<List<CategoryWithProducts>>(subscribeScheduler, postExecutionScheduler) {
    override fun createUseCase(): Flowable<List<CategoryWithProducts>> {
        return repository.getCategoriesList()
            .flattenAsFlowable { it }
            .flatMap { category ->
                repository.getProductsByCategoryId(category.category_id)
                    .toFlowable()
                    .map { CategoryWithProducts(category, it) }
            }.toList().toFlowable()
    }
}


