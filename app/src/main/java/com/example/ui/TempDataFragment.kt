package com.example.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.R
import com.example.entity.Env
import com.example.entity.Temp
import com.example.ui.Adapter.TempAdapter
import java.text.SimpleDateFormat
import java.time.Instant.now
import java.util.*
import kotlin.collections.ArrayList

class TempDataFragment:Fragment() {
    private lateinit var lv_temp:RecyclerView
    private lateinit var mContext: Context

    companion object{
        private var instance:Fragment? = null
        @JvmStatic
        fun newInstance():Fragment{
            if(instance == null){
                instance = TempDataFragment()
            }
            return instance!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        mContext = requireContext()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val v = inflater.inflate(R.layout.fragment_temp_data,container,false)

        val tempList = ArrayList<Temp>()
        for(i in 1..10){
            tempList.add(Temp("name"+i,true,18,36f, Date(System.currentTimeMillis())))
        }

        lv_temp = v.findViewById(R.id.lv_temp)
        lv_temp.layoutManager = LinearLayoutManager(mContext)
        lv_temp.adapter = TempAdapter(mContext,tempList)
        Log.e("TAG","size"+tempList.size);
        return v
    }
}