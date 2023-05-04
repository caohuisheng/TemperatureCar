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
        lv_env.adapter = MyViewAdapter(envList,mContext)
        lv_env.layoutManager = LinearLayoutManager(mContext)
        return v
    }

    class MyViewAdapter(val envList:ArrayList<Env>, val context: Context): RecyclerView.Adapter<MyViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = View.inflate(context, R.layout.item_env_data,null)
            val viewHolder = MyViewHolder(view)
            return viewHolder
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val temp: Env = envList.get(position)
            holder.tv_temp.setText(temp.temp.toString())
            holder.tv_voc.setText(temp.voc.toString())
            holder.tv_co2.setText(temp.co2.toString())
            holder.tv_dist.setText(temp.dist.toString())
        }

        override fun getItemCount(): Int {
            return envList.size
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_temp:TextView = itemView.findViewById(R.id.tv_temp)
        val tv_voc:TextView = itemView.findViewById(R.id.tv_voc)
        val tv_co2:TextView = itemView.findViewById(R.id.tv_co2)
        val tv_dist:TextView = itemView.findViewById(R.id.tv_dist)
    }

}