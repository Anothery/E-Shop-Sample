package com.example.eshopsample.ui.detail

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.eshopsample.R
import com.example.eshopsample.domain.model.ProductDetail
import com.example.eshopsample.utils.PRODUCT_DETAILS_ID
import dagger.android.AndroidInjection
import javax.inject.Inject

class DetailActivity : DetailContract.View, AppCompatActivity() {
    @Inject
    lateinit var presenter: DetailContract.Presenter

    private lateinit var tvDescription: TextView
    private lateinit var tvProductLabel: TextView
    private lateinit var tvPrice: TextView
    private lateinit var tvRelated: TextView
    private lateinit var ivProductImage: ImageView
    private lateinit var toolbar: Toolbar
    private lateinit var svDetails: ScrollView
    private lateinit var llErrorScreen: LinearLayout
    private lateinit var rvRelated: RecyclerView

    private lateinit var adapter: DetailRelatedAdapter
    private val relatedProducts = ArrayList<ProductDetail>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        AndroidInjection.inject(this)
        setupViews()
        setupToolbar()
        setupRecyclerView()
        presenter.onAttach(this)

        val id = intent.getIntExtra(PRODUCT_DETAILS_ID, 0)
        presenter.initialize(id, relatedProducts)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = getString(R.string.details_title)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                presenter.onHomePressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
    }

    private fun setupViews() {
        tvDescription = findViewById(R.id.detail_tv_description)
        tvProductLabel = findViewById(R.id.detail_tv_product_label)
        ivProductImage = findViewById(R.id.detail_iv_product_image)
        llErrorScreen = findViewById(R.id.detail_error_screen)
        tvPrice = findViewById(R.id.detail_tv_price)
        toolbar = findViewById(R.id.toolbar)
        rvRelated = findViewById(R.id.detail_rv_related)
        tvRelated = findViewById(R.id.detail_tv_related_label)
    }

    private fun setupRecyclerView() {
        rvRelated.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter = DetailRelatedAdapter(relatedProducts, this, presenter)
        rvRelated.adapter = adapter
    }

    override fun updateRelatedList() {
        adapter.notifyDataSetChanged()
    }

    override fun hideRelatedProducts() {
        tvRelated.visibility = View.GONE
        rvRelated.visibility = View.GONE
    }

    override fun showRelatedProducts() {
        tvRelated.visibility = View.VISIBLE
        rvRelated.visibility = View.VISIBLE
    }


    override fun openDetails(id: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(PRODUCT_DETAILS_ID, id)
        startActivity(intent)
        overridePendingTransition(R.anim.enter, R.anim.exit)
    }

    override fun loadData(productDetails: ProductDetail) {
        tvProductLabel.text = productDetails.productName
        tvDescription.text = Html.fromHtml(productDetails.productDescription)
        tvPrice.text = productDetails.price_formated
        Glide
            .with(this)
            .load(productDetails.original_image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.baseline_broken_image_24)
            .into(ivProductImage)

    }

    override fun showErrorScreen() {
        svDetails.visibility = View.GONE
        llErrorScreen.visibility = View.VISIBLE
    }

    override fun finishActivity() {
        finish()
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}
