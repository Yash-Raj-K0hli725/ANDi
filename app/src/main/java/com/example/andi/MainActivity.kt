package com.example.andi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.andi.databinding.ActivityMainBinding
import com.example.andi.viewModel.mainModel
import com.example.andi.viewModel.vmFactory
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var bind: ActivityMainBinding
    lateinit var mainvm: mainModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainvm = ViewModelProvider(this, vmFactory(this))[mainModel::class.java]
        bind.host.adapter = mainvm.vpAdapter
        TabLayoutMediator(bind.tabs, bind.host) { tabs, position ->
            tabs.text = mainvm.tabsName[position]
        }.attach()
    }

}