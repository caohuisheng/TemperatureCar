package com.example.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*

class MineFragment() : Fragment() {
    private lateinit var lineChart: LineChart
    private lateinit var barChart: BarChart
    private lateinit var pieChart: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_mine,container,false)
        initView(v)
        return v
    }

    private fun initView(v: View) {
        lineChart = v.findViewById(R.id.line_chart)
        barChart = v.findViewById(R.id.bar_chart)
        pieChart = v.findViewById(R.id.pieChart)
        showLineChart()
        showBarChart()
        showPieChart()
    }

    private fun showLineChart(){
        var entries:ArrayList<Entry> = ArrayList<Entry>()
        for(i in 0..5){
            val v = (Math.random()*10+1).toFloat()
            entries.add(Entry(i.toFloat(),v))
        }
        var dataSet = LineDataSet(entries,"aaa")
        lineChart.data = LineData(dataSet)

        //X轴所在位置   默认为上面
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        //隐藏右边的Y轴
        lineChart.getAxisRight().setEnabled(false);

        lineChart.getXAxis().setDrawGridLines(false);  //是否绘制X轴上的网格线（背景里面的竖线）
        lineChart.getAxisLeft().setDrawGridLines(false);  //是否绘制Y轴上的网格线（背景里面的横线）

        var legend = lineChart.getLegend();
        legend.setEnabled(false);    //是否显示图例

        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        //是否画折线点上的空心圆  false表示直接画成实心圆
        dataSet.setDrawCircleHole(false);


        //dataSet.setColor(R.color.my_orange)
        var context = activity;
        if(context != null){
            val color = context.getApplicationContext().getResources().getColor(R.color.orange2)
            dataSet.setColor(color)
        }
        dataSet.setDrawCircles(false)

        //dataSet.setColor(activity?.getApplicationContext()?.getResources()?.getColor(R.color.bg_blue))
    }

    private fun showBarChart(){
        var entries = ArrayList<BarEntry>()
        for(i in 0..8){
            val v = (Math.random()*10+1).toFloat()
            entries.add(BarEntry(i.toFloat(),v))
        }
        var dataSet = BarDataSet(entries,"aaa")
        barChart.data = BarData(dataSet)

        barChart.getDescription().setEnabled(false);//隐藏右下角英文
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);//X轴的位置 默认为上面
        barChart.getAxisRight().setEnabled(false);//隐藏右侧Y轴   默认是左右两侧都有Y轴

        barChart.getXAxis().setDrawGridLines(false);  //是否绘制X轴上的网格线（背景里面的竖线）
        barChart.getAxisLeft().setDrawGridLines(false);  //是否绘制Y轴上的网格线（背景里面的横线）

        var legend = barChart.getLegend();
        legend.setEnabled(false);    //是否显示图例

        var context = activity;
        if(context != null){
            val color = context.getApplicationContext().getResources().getColor(R.color.my_green)
            dataSet.setColor(color)
        }
    }

    private fun showPieChart(){
        var entries = ArrayList<PieEntry>()
        entries.add(PieEntry(30.0.toFloat(),"睡眠"))
        entries.add(PieEntry(20.0.toFloat(),"锻炼"))
        entries.add(PieEntry(40.0.toFloat(),"饮水"))
        var dataSet = PieDataSet(entries,"")

        pieChart.data = PieData(dataSet)
        //val a:Int = R.color.my_green
        Log.e("TAG",""+R.color.my_green)
        Log.e("TAG",""+ Color.RED)

        //dataSet.setColors(Color.ORANG)
        var context = activity;
        if(context != null){
            val color1 = context.getApplicationContext().getResources().getColor(R.color.my_green)
            val color2 = context.getApplicationContext().getResources().getColor(R.color.my_orange)
            val color3 = context.getApplicationContext().getResources().getColor(R.color.my_blue)
            dataSet.setColors(color1,color2,color3)
        }

        pieChart.setTransparentCircleRadius(0f);
        //pieChart.setHoleColor()
    }

}