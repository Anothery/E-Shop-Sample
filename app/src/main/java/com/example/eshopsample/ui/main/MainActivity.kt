package com.example.eshopsample.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eshopsample.R
import com.example.eshopsample.domain.model.Category
import com.example.eshopsample.domain.model.CategoryWithProducts
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {
    @Inject
    lateinit var presenter: MainContract.Presenter

    private lateinit var rvCategories: RecyclerView
    private lateinit var adapter: MainCategoriesListAdapter
    private val categories = ArrayList<CategoryWithProducts>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {
            presenter = lastCustomNonConfigurationInstance as MainContract.Presenter }

        if (!this::presenter.isInitialized) {
            AndroidInjection.inject(this)
        }
        presenter.onAttach(this)

        setupViews()
        setupRecyclerView()
        presenter.initialize(categories)
    }


    private fun setupViews() {
        rvCategories = findViewById(R.id.main_rv)
    }

    private fun setupRecyclerView() {
        rvCategories.layoutManager = LinearLayoutManager(this)
        adapter = MainCategoriesListAdapter(categories, this)
        rvCategories.adapter = adapter
    }

    override fun updateCategories() {
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

}
