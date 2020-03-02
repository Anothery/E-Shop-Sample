package com.example.eshopsample

import com.example.eshopsample.domain.model.CategoryWithProducts
import com.example.eshopsample.domain.usecase.UseCaseGetCategoryWithProducts
import com.example.eshopsample.ui.main.MainContract
import com.example.eshopsample.ui.main.MainPresenter
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

class MainPresenterTest {
    private val useCaseGetCategoryWithProducts: UseCaseGetCategoryWithProducts = mock()
    private val view: MainContract.View = mock()
    private lateinit var presenter: MainPresenter

    @Before
    fun setup() {
        presenter = MainPresenter(useCaseGetCategoryWithProducts)
        presenter.onAttach(view)
    }

    @Test
    fun test_initialize_viewsChanged() {
        val list: ArrayList<CategoryWithProducts> = mock()

        presenter.initialize(list)

        verify(view, times(1)).hideRecyclerView()
        verify(view, times(1)).showProgressBar()
    }

    @Test
    fun test_initialize_useCaseExecuted() {
        val list: ArrayList<CategoryWithProducts> = mock()

        presenter.initialize(list)

        verify(useCaseGetCategoryWithProducts, times(1)).subscribe(any())
    }

    @Test
    fun test_onDetach_useCaseDisposed() {
        presenter.onDetach()

        verify(useCaseGetCategoryWithProducts, times(1)).dispose()
    }

}
