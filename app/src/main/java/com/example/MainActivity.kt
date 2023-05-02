package com.example

import android.os.Bundle
import android.widget.ImageButton

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
        cancelTitle()
        setContentView(R.layout.activity_shexiang)
        btn_control_car = findViewById(R.id.btn_control_car)

        btn_control_car.setOnClickListener {
            //var intent = Intent(this,PersonalDataActivity)
            //startActivity(intent)
        }
    }
}

