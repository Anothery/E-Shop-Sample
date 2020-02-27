package com.example.eshopsample.domain.usecase


import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.DisposableSubscriber

abstract class UseCase<T>(
    private val subscribeScheduler: Scheduler,
    private val postExecutionScheduler: Scheduler
) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun subscribe(disposableSubscriber: DisposableSubscriber<T>) {
        val flowable: Flowable<T> =
            createUseCase()
                .subscribeOn(subscribeScheduler)
                .observeOn(postExecutionScheduler)
        compositeDisposable.add(flowable.subscribeWith(disposableSubscriber))
    }

    fun dispose() {
        compositeDisposable.clear()
    }

    protected abstract fun createUseCase(): Flowable<T>
}