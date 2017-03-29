package com.example.cw.fumesmanage.Tools;

import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

/**
 * Created by cw on 2017/3/30.
 */

public class RealChartMaker {

    private String[] strings;
    private double[] doubles;
    private LineChart lineChart;

    public RealChartMaker(String[] strings, double[] doubles, LineChart lineChart) {
        this.strings = strings;
        this.doubles = doubles;
        this.lineChart = lineChart;
    }



    public void makeChart(){

        if(RealChartMaker.this.doubles!=null){
            LineData mLineData = getLineData();
            showChart(RealChartMaker.this.lineChart, mLineData, Color.rgb(255, 255, 255));
        }

    }

    // 设置显示的样式
    private void showChart(LineChart lineChart, LineData lineData, int color) {
        lineChart.setDrawBorders(false);  //是否在折线图上添加边框

//        // no description text
        lineChart.setDescription(null);// 数据描述
//        // 如果没有数据的时候，会显示这个，类似listview的emtpyview
//        lineChart.setNoDataTextDescription("You need to provide data for the chart.");

        // enable / disable grid background
        lineChart.setDrawGridBackground(false); // 是否显示表格颜色
        lineChart.setGridBackgroundColor(Color.WHITE); // 表格的的颜色，在这里是是给颜色设置一个透明度



        // enable touch gestures
        lineChart.setTouchEnabled(true); // 设置是否可以触摸

        // 不显示y轴右边的值
        lineChart.getAxisRight().setEnabled(false);
        XAxis xAxis = lineChart.getXAxis();
        YAxis leftAxis = lineChart.getAxisLeft();

        LimitLine yLimitLine = new LimitLine(2.0f,"超标线 2.0mg/m³");
        yLimitLine.setTextSize(15f);
        yLimitLine.setLineColor(Color.RED);
        yLimitLine.setTextColor(Color.RED);

//        LimitLine yLimitLinewarn = new LimitLine(1.9f,"报警线 1.9mg/m³");
//        yLimitLinewarn.setTextSize(15f);
//        yLimitLinewarn.setLineColor(Color.rgb(255,153,18));
//        yLimitLinewarn.setTextColor(Color.RED);


        leftAxis.addLimitLine(yLimitLine);
//        leftAxis.addLimitLine(yLimitLinewarn);

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                return RealChartMaker.this.strings[(int)value];
            }

        };
        //定制X轴起点和终点Label不能超出屏幕。
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
//        xAxis.setValueFormatter(formatter);

        // enable scaling and dragging
        lineChart.setDragEnabled(true);// 是否可以拖拽
        lineChart.setScaleEnabled(true);// 是否可以缩放

        // if disabled, scaling can be done on x- and y-axis separately
        lineChart.setPinchZoom(false);//

        lineChart.setBackgroundColor(color);// 设置背景

        // add data
        lineChart.setData(lineData); // 设置数据

        // get the legend (only possible after setting data)
        Legend mLegend = lineChart.getLegend(); // 设置比例图标示，就是那个一组y的value的

        // modify the legend ...
        // mLegend.setPosition(LegendPosition.LEFT_OF_CHART);
        mLegend.setForm(Legend.LegendForm.CIRCLE);// 样式
        mLegend.setFormSize(10f);// 字体
        mLegend.setTextColor(Color.BLACK);// 颜色
//      mLegend.setTypeface(mTf);// 字体

//        lineChart.animateX(1500); // 立即执行的动画,x轴
        lineChart.animateXY(1000,1000);
    }

    /**
     * 生成一个数据
     *
     * @return
     */
    private LineData getLineData() {


        // y轴的数据
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        //System.out.println(RealChartMaker.this.doubles.length+ "  123121");
        if(RealChartMaker.this.doubles!=null){
            for(int i = 0; i < RealChartMaker.this.doubles.length ;i++){
                Entry y = new Entry(i, (float)doubles[i]) ;
                yValues.add(y);

            }
        }




        // create a dataset and give it a type
        // y轴的数据集合
        LineDataSet lineDataSet = new LineDataSet(yValues, "油烟检测图" /*显示在比例图上*/);
        // mLineDataSet.setFillAlpha(110);
        // mLineDataSet.setFillColor(Color.RED);

        //用y轴的集合来设置参数
        lineDataSet.setLineWidth(1.75f); // 线宽
        lineDataSet.setCircleSize(5f);// 显示的圆形大小
        lineDataSet.setColor(Color.rgb(0,153,0));// 显示颜色
        lineDataSet.setCircleColor(Color.rgb(0,153,0));// 圆形的颜色
//        lineDataSet.setHighLightColor(R.color.colorBule); // 高亮的线的颜色
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);//曲线风格
        lineDataSet.setCubicIntensity(0.2f);//设置曲线的平滑度
        lineDataSet.setDrawFilled(true);//设置允许填充


        ArrayList<LineDataSet> lineDataSets = new ArrayList<LineDataSet>();
        lineDataSets.add(lineDataSet); // add the datasets

        // create a data object with the datasets
        LineData lineData = new LineData(lineDataSet);


        return lineData;
    }
}
