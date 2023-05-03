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
import com.example.entity.Temp
import java.text.SimpleDateFormat
import java.time.Instant.now
import java.util.*
import kotlin.collections.ArrayList

class TempDataFragment:Fragment() {
    private lateinit var instance:Fragment
    private lateinit var tempList:ArrayList<Temp>
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
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val v = layoutInflater.inflate(R.layout.fragment_temp_data,container,false)

        tempList = ArrayList()
        for(i in 1..10){
            tempList.add(Temp("name"+i,true,18,36f, Date(System.currentTimeMillis())))
        }
        mContext = requireContext()
        lv_temp = v.findViewById(R.id.lv_temp)
        lv_temp.adapter = MyViewAdapter(tempList,mContext)
        lv_temp.layoutManager = LinearLayoutManager(mContext)
        Log.e("TAG","size"+tempList.size);
        return v
    }

    class MyViewAdapter(val tempList:ArrayList<Temp>,val context:Context):RecyclerView.Adapter<MyViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = View.inflate(context,R.layout.item_temp_data,null)
            val viewHolder = MyViewHolder(view)
            return viewHolder
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val temp:Temp = tempList.get(position)
            holder.tv_name.setText(temp.name)
            //temp.sex==true;
            var sex:String
            if(temp.sex){
                sex = "男"
            }else{
                sex = "女"
            }
            holder.tv_sex.setText(sex)
            holder.tv_age.setText(temp.age.toString());
            holder.tv_temp.setText(temp.temp.toString())
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
            val date = formatter.format(temp.time)
            holder.tv_time.setText(date)
        }

        override fun getItemCount(): Int {
            return tempList.size
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_name:TextView = itemView.findViewById(R.id.tv_name)
        val tv_sex:TextView = itemView.findViewById(R.id.tv_sex)
        val tv_age:TextView = itemView.findViewById(R.id.tv_age)
        val tv_temp:TextView = itemView.findViewById(R.id.tv_temp)
        val tv_time:TextView = itemView.findViewById(R.id.tv_time)
    }
}