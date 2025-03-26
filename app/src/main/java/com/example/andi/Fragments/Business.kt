package com.example.andi.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andi.R
import com.example.andi.Utils.htmlData
import com.example.andi.Utils.sectorListAdapter
import com.example.andi.databinding.FragmentBusinessBinding
import com.example.andi.viewModel.mainModel
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class Business : Fragment() {
    lateinit var bind:FragmentBusinessBinding
    lateinit var mainvm:mainModel
    private val sectorListAdapter = sectorListAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = DataBindingUtil.inflate(inflater,R.layout.fragment_business,container,false)
        mainvm = ViewModelProvider(requireActivity())[mainModel::class.java]

        mainvm.pageData.observe(viewLifecycleOwner){
                filterPage(it)
        }
        bind.sectorList.adapter = sectorListAdapter
        bind.sectorList.layoutManager = LinearLayoutManager(this.context)
        // Inflate the layout for this fragment
        return bind.root
    }

    fun filterPage(pageData:List<htmlData>){
        val idk = mutableListOf<htmlData>()
        pageData.forEach {
            when(it.sector){
            "Sector - Business"->idk.add(it)
            }
        }
        bind.loader.visibility = View.GONE
        sectorListAdapter.submitList(idk)
    }

}