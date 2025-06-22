package com.example.ecommerce.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerce.Domain.ItemsModel
import com.example.ecommerce.databinding.PopularViewholderBinding

class PopularAdapter(val items: MutableList<ItemsModel>) :
    RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

        lateinit var context: Context

    inner class PopularViewHolder(val binding: PopularViewholderBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        context = parent.context
        val binding = PopularViewholderBinding.inflate(LayoutInflater.from(context), parent, false)
        return PopularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.binding.titleTv.text =  items[position].title
        holder.binding.subtitleTv.text =  items[position].extra
        holder.binding.priceTv.text =  "$-" +items[position].price.toString()

        Glide.with(context)
            .load(items[position].picUrl[0])
            .into(holder.binding.imageView5)

        holder.binding.view.setOnClickListener {

        }
    }

    override fun getItemCount(): Int = items.size
}