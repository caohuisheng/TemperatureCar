package com.example.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.R
import com.example.entity.Env
import com.example.entity.Temp
import com.example.ui.Adapter.EnvAdapter
import com.example.ui.Adapter.TempAdapter

class EnvDataFragment:Fragment() {
    private lateinit var lv_env:RecyclerView
    private lateinit var mContext:Context

    companion object{
        private var instance:Fragment? = null
        @JvmStatic
        fun newInstance():Fragment{
            if(instance == null){
                instance = EnvDataFragment()
            }
            return instance!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_env_data,container,false)
        lv_env = v.findViewById(R.id.lv_env)
        var envList = ArrayList<Env>()
        for(i in 1..10){
            envList.add(Env(36f,20f,10f,20f))
        }
        mContext = requireContext()
        lv_env.layoutManager = LinearLayoutManager(mContext)
        lv_env.adapter = EnvAdapter(mContext,envList)
        return v
    }

}