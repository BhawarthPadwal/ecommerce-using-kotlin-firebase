package com.example.ecommerce.Adapter

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerce.Activity.ItemsListActivity
import com.example.ecommerce.Domain.CategoryModel
import com.example.ecommerce.R
import com.example.ecommerce.databinding.CategoryViewholderBinding


class CategoryAdapter(val items: MutableList<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private lateinit var context: Context
    private var selectedPosition = -1
    private var lastSelectedPosition = -1

    inner class ViewHolder(val binding: CategoryViewholderBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        context = parent.context
        var binding = CategoryViewholderBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        var item = items[position]
        holder.binding.categoryTitleTv.text = item.title

        holder.binding.root.setOnClickListener {
            val adapterPosition = holder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                lastSelectedPosition = selectedPosition
                selectedPosition = adapterPosition
                notifyItemChanged(lastSelectedPosition)
                notifyItemChanged(selectedPosition)

                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(context, ItemsListActivity::class.java).apply {
                        putExtra("id", item.id.toString())
                        putExtra("title", item.title)
                    }
                    ContextCompat.startActivity(context, intent, null)
                }, 500)
            }
        }
        if (selectedPosition == position) {
            holder.binding.root.setBackgroundResource(R.drawable.brown_full_corner_bg)
            holder.binding.categoryTitleTv.setTextColor(
                ContextCompat.getColor(context, R.color.white)
            )
        } else {
            holder.binding.root.setBackgroundResource(R.drawable.white_full_corner_bg)
            holder.binding.categoryTitleTv.setTextColor(
                ContextCompat.getColor(context, R.color.darkBrown)
            )
        }
    }

    override fun getItemCount(): Int = items.size
}