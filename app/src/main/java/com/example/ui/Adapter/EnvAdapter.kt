package com.example.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.R
import com.example.entity.Env

class EnvAdapter(val context:Context,val envList:ArrayList<Env>):
    RecyclerView.Adapter<EnvAdapter.EnvHolder>() {

    class EnvHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val tv_temp:TextView = itemView.findViewById(R.id.tv_temp)
        val tv_voc:TextView = itemView.findViewById(R.id.tv_voc)
        val tv_co2:TextView = itemView.findViewById(R.id.tv_co2)
        val tv_dist:TextView = itemView.findViewById(R.id.tv_dist)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnvHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_env_data,parent,false)
        return EnvHolder(view)
    }

    override fun onBindViewHolder(holder: EnvHolder, position: Int) {
        val env = envList.get(position)
        holder.tv_temp.setText(env.temp.toString())
        holder.tv_voc.setText(env.voc.toString())
        holder.tv_co2.setText(env.co2.toString())
        holder.tv_dist.setText(env.dist.toString())
    }

    override fun getItemCount(): Int {
        return envList.size
    }
}