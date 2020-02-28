package com.example.eshopsample.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eshopsample.R
import com.example.eshopsample.domain.model.Product
import com.example.eshopsample.utils.DecodeHTMLUtils

class MainProductsListAdapter(
    private val products: List<Product>,
    private val context: Context
) :
    RecyclerView.Adapter<MainProductsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvProductName.text =
            DecodeHTMLUtils.decodeString(products[position].name)
        Glide.with(context).load(products[position].image).into(holder.ivProductPhoto)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvProductName: TextView = itemView.findViewById(R.id.product_list_tv_name)
        val ivProductPhoto: ImageView = itemView.findViewById(R.id.product_list_iv_photo)
    }
}