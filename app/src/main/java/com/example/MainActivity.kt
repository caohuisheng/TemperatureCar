package com.example

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.entity.Temp
import com.example.ui.Adapter.TempAdapter
import java.util.*
import kotlin.collections.ArrayList

/*List<String> xDataList = new ArrayList<>();// x轴数据源
List<Entry> yDataList = new ArrayList<>();// y轴数据数据源
//给上面的X、Y轴数据源做假数据测试
for (int i = 0; i < 24; i++) {
    // x轴显示的数据
    xDataList.add(i + ":00");
    //y轴生成float类型的随机数
    float value = (float) (Math.random() * range) + 3;
    yDataList.add(new Entry(value, i));
}

//显示图表,参数（ 上下文，图表对象， X轴数据，Y轴数据，图表标题，曲线图例名称，坐标点击弹出提示框中数字单位）
ChartUtil.showChart(this, lineChart, xDataList, yDataList, "供热趋势图", "供热量/时间","kw/h");*/

class MainActivity : StatusBar() {
    private lateinit var btn_control_car:ImageButton;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //cancelTitle()
        setContentView(R.layout.activity_main)
        val lv:RecyclerView = findViewById(R.id.lv_main) as RecyclerView;
        val btn_update:Button = findViewById(R.id.btn_update)
        val tempList = ArrayList<Temp>()
        for(i in 1..10){
            tempList.add(Temp("name"+i,true,18,36f, Date(System.currentTimeMillis())))
        }

        val layoutManager = LinearLayoutManager(this)
        lv.layoutManager = layoutManager
        Log.e("TAG","size"+tempList.size)
        lv.adapter = TempAdapter(this,tempList)
    }
}

