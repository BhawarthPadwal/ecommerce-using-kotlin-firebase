package com.example.ecommerce.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommerce.Adapter.ItemsListAdapter
import com.example.ecommerce.ViewModel.MainViewModel
import com.example.ecommerce.databinding.ActivityItemsListBinding

class ItemsListActivity : AppCompatActivity() {

    lateinit var binding: ActivityItemsListBinding
    private val viewModel =  MainViewModel()
    private var id: String = ""
    private var title: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityItemsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundles()
        initList()
    }

    private fun initList() {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            viewModel.loadItems(id).observe(this@ItemsListActivity, Observer {
                itemsView.layoutManager = GridLayoutManager(this@ItemsListActivity, 2)
                itemsView.adapter = ItemsListAdapter(it)
                progressBar.visibility = View.GONE
            })
            backBtn.setOnClickListener {
                finish()
            }
        }
    }

    private fun getBundles() {
        id = intent.getStringExtra("id")!!
        title = intent.getStringExtra("title")!!

        binding.listTitleTv.text = title
    }
}