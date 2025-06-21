package com.example.ecommerce.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerce.Domain.BannerModel
import com.example.ecommerce.Repository.MainRepository

class MainViewModel : ViewModel() {

    private val repository = MainRepository()

    fun loadBanner(): LiveData<MutableList<BannerModel>> {
        return repository.loadBanner()
    }
}