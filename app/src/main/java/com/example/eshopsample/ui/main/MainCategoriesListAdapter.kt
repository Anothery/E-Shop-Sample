package com.example.eshopsample.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eshopsample.R
import com.example.eshopsample.domain.model.CategoryWithProducts
import com.example.eshopsample.domain.model.ProductDetail
import com.example.eshopsample.utils.fromHtml


class MainCategoriesListAdapter(
    private val categories: ArrayList<CategoryWithProducts>,
    private val context: Context,
    private val presenter: MainContract.Presenter
) :
    RecyclerView.Adapter<MainCategoriesListAdapter.ViewHolder>() {

    private val viewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.categories_list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvCategoryName.text = categories[position].category.name.fromHtml()

        if (categories[position].products.isEmpty()) {
            holder.recyclerView.visibility = View.GONE
            holder.tvNoProducts.visibility = View.VISIBLE
        } else {
            holder.recyclerView.visibility = View.VISIBLE
            holder.tvNoProducts.visibility = View.GONE
            setupRecyclerView(holder, categories[position].products)
        }
    }

    private fun setupRecyclerView(holder: ViewHolder, products: List<ProductDetail>) {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        layoutManager.initialPrefetchItemCount = products.size

        holder.recyclerView.setRecycledViewPool(viewPool)
        holder.recyclerView.layoutManager = layoutManager
        holder.recyclerView.adapter = MainProductsListAdapter(products, context, presenter)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvCategoryName: TextView = itemView.findViewById(R.id.categories_list_tv_name)
        var recyclerView: RecyclerView = itemView.findViewById(R.id.categories_list_rv)
        var tvNoProducts: TextView = itemView.findViewById(R.id.categories_list_no_products)
    }
}
