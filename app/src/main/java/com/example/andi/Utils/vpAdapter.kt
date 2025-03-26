package com.example.andi.Utils

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.andi.MainActivity

class vpAdapter(context: MainActivity, private val list:List<Fragment>):FragmentStateAdapter(context) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }
}