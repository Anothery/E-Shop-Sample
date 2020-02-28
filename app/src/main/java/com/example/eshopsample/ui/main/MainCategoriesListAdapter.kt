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
import com.example.eshopsample.utils.DecodeHTMLUtils


class MainCategoriesListAdapter(
    private val categories: ArrayList<CategoryWithProducts>,
    private val context: Context
) :
    RecyclerView.Adapter<MainCategoriesListAdapter.ViewHolder>() {

    private val viewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.categories_list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvCategoryName.text =
            DecodeHTMLUtils.decodeString(categories[position].category.name)
        holder.recyclerView.setRecycledViewPool(viewPool)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        layoutManager.initialPrefetchItemCount = categories[position].products.size

        holder.recyclerView.layoutManager = layoutManager
        holder.recyclerView.adapter = MainProductsListAdapter(categories[position].products, context)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvCategoryName: TextView = itemView.findViewById(R.id.categories_list_tv_name)
        var recyclerView: RecyclerView = itemView.findViewById(R.id.categories_list_rv)
    }
}
