package com.example.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.RecyclerView

import androidx.viewpager2.widget.ViewPager2
import com.example.R
import com.example.View.CustomPageTransformer
import com.example.View.RotateSelector
import com.example.ViewModel.BlueViewModel

class HomeFragment(): Fragment() {

    private lateinit var viewPager: ViewPager2
    private var isFirst = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewModel = ViewModelProvider(requireActivity()).get(BlueViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home,container,false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        val list = ArrayList<Fragment>()
        list.add(PageFragment.newInstance(R.mipmap.car))
        list.add(PageFragment.newInstance(R.mipmap.monitor))
        viewPager = view.findViewById(R.id.home_viewPager)
        val bannerAdapter = BannerAdapter(list, requireActivity())
        viewPager.adapter = bannerAdapter
        viewPager.setPageTransformer(CustomPageTransformer())
        viewPager.offscreenPageLimit = 3
        viewPager.apply {
            val recyclerView = getChildAt(0) as RecyclerView
            recyclerView.apply {
                val padding =2 * resources.getDimensionPixelOffset(R.dimen.dp_50)
                setPadding(padding,0,padding,0)
                clipToPadding = false

            }
        }
        val rotateSelector = view.findViewById<RotateSelector>(R.id.home_rotate)
        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (!isFirst) {
                    rotateSelector.rotateView()
                } else {
                    isFirst = false
                }
            }
        })
    }


}