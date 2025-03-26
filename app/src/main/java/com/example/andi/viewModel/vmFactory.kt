package com.example.andi.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.andi.MainActivity

class vmFactory(private val context: MainActivity):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return mainModel(context) as T
    }
}