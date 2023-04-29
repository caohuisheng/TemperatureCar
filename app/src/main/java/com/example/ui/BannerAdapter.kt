package com.example.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity


import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter

class BannerAdapter(list:List<Fragment>,fa:FragmentActivity):FragmentStateAdapter(fa){

    private val list:List<Fragment>

    init {
        this.list = list

    }



    override fun getItemCount(): Int {
       return list.count()
    }

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }

}


