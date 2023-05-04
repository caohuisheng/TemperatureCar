package com.example.ui.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.recyclerview.widget.RecyclerView
import com.example.R
import com.example.entity.Temp
import java.text.SimpleDateFormat

class TempAdapter(val context:Context, val tempList:ArrayList<Temp>): RecyclerView.Adapter<TempAdapter.TempHolder>() {
    class TempHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_name: TextView = itemView.findViewById(R.id.tv_name)
        val tv_sex: TextView = itemView.findViewById(R.id.tv_sex)
        val tv_age: TextView = itemView.findViewById(R.id.tv_age)
        val tv_temp: TextView = itemView.findViewById(R.id.tv_temp1)
        val tv_time: TextView = itemView.findViewById(R.id.tv_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TempHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_temp_data,parent,false)
        val tempHolder = TempHolder(view)
        return tempHolder
    }

    override fun onBindViewHolder(holder: TempHolder, position: Int) {
        val temp:Temp = tempList.get(position)
        holder.tv_name.setText(temp.name)
        var sex:String
        if(temp.sex){
            sex = "男"
        }else{
            sex = "女"
        }
        holder.tv_sex.setText(sex)
        holder.tv_age.setText(temp.age.toString());
        holder.tv_temp.setText(temp.temp.toString())
        val formatter = SimpleDateFormat("HH:mm")
        val date = formatter.format(temp.time)
        holder.tv_time.setText(date)
    }

    override fun getItemCount(): Int {
        Log.e("TAG","size1:"+tempList.size)
        return tempList.size
    }
}