package com.example

import android.os.Bundle
import android.os.PersistableBundle
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry

class PersonalDataActivity:StatusBar() {
    private lateinit var lineChart: LineChart
    private lateinit var barChart: BarChart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cancelTitle()
        setContentView(R.layout.activity_personal_data)

        lineChart = findViewById(R.id.line_chart);
        var xDataList = ArrayList<String>();
        var yDataList = ArrayList<Entry>();

        var range = 30
        for(i in 1..10){
            xDataList.add("{i}:00")
            var value:Float = ((Math.random()*range)+3).toFloat()
            yDataList.add(Entry(value,i))
        }
        ChartUtils.showChart(this,lineChart,xDataList,yDataList,
            "供热趋势图", "供热量/时间","kw/h")

        barChart = findViewById(R.id.bar_chart)

        var barEntryList = ArrayList<BarEntry>()
        for(i in 0..5){
            var v = Math.random()*10+1;
            barEntryList.add(BarEntry(v.toFloat(),i))
//            var i = BarEntry(1.2,1)
        }
        /*bar = (BarChart) findViewById(R.id.bar);
        //添加数据
        list.add(new BarEntry(1,3));
        list.add(new BarEntry(2,8));
        list.add(new BarEntry(3,6));
        list.add(new BarEntry(4,9));

        BarDataSet barDataSet=new BarDataSet(list,"语文");
        BarData barData=new BarData(barDataSet);
        bar.setData(barData);

        bar.getDescription().setEnabled(false);//隐藏右下角英文
        bar.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);//X轴的位置 默认为上面
        bar.getAxisRight().setEnabled(false);//隐藏右侧Y轴   默认是左右两侧都有Y轴*/
    }

}