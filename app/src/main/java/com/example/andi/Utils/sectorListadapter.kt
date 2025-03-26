package com.example.andi.Utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.andi.databinding.ItemLayBinding

class sectorListAdapter:ListAdapter<htmlData,sectorListAdapter.listVH>(dif()) {
    inner class listVH(val bind:ItemLayBinding):RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): listVH {
       return listVH(ItemLayBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: listVH, position: Int) {
        val currentItem = getItem(position)
       holder.apply {
           bind.price.text = currentItem.price
           bind.header.text = currentItem.header
           bind.desc.text = currentItem.desc
       }
    }
}
class dif:DiffUtil.ItemCallback<htmlData>(){
    override fun areItemsTheSame(oldItem: htmlData, newItem: htmlData): Boolean {
        return oldItem.header == newItem.header
    }

    override fun areContentsTheSame(oldItem: htmlData, newItem: htmlData): Boolean {
        return oldItem.desc == newItem.desc
    }
}