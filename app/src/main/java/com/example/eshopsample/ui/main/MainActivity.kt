package com.example.eshopsample.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eshopsample.R
import com.example.eshopsample.domain.model.CategoryWithProducts
import com.example.eshopsample.ui.detail.DetailActivity
import com.example.eshopsample.utils.PRODUCT_DETAILS_ID
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {
    @Inject
    lateinit var presenter: MainContract.Presenter

    private lateinit var progressBar: ProgressBar
    private lateinit var rvCategories: RecyclerView
    private lateinit var adapter: MainCategoriesListAdapter
    private val categories = ArrayList<CategoryWithProducts>()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onAttach(this)

        setupViews()
        setupRecyclerView()

        presenter.initialize(categories)
    }


    private fun setupViews() {
        rvCategories = findViewById(R.id.main_rv)
        progressBar = findViewById(R.id.main_progressbar)
    }

    private fun setupRecyclerView() {
        rvCategories.layoutManager = LinearLayoutManager(this)
        adapter = MainCategoriesListAdapter(categories, this, presenter)
        rvCategories.adapter = adapter
    }

    override fun openDetails(id: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(PRODUCT_DETAILS_ID, id)
        startActivity(intent)
        overridePendingTransition(R.anim.enter, R.anim.exit)
    }

    override fun updateCategories() {
        adapter.notifyDataSetChanged()
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showRecyclerView() {
        rvCategories.visibility = View.VISIBLE
    }

    override fun hideRecyclerView() {
        rvCategories.visibility = View.GONE
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

}
