package com.example.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import com.example.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

class PageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var rId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            rId = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_page, container, false)
        initView(v)
        return v
    }

    private fun initView(v:View?) {
        val imageView = v?.findViewById<AppCompatImageView>(R.id.banner_imageView)
        imageView?.setImageDrawable(rId?.let { requireContext().getDrawable(it) })
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int) =
            PageFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, id)
                }
            }
    }
}