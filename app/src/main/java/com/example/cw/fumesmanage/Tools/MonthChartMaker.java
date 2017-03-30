package com.example.cw.fumesmanage.Tools;

import android.graphics.Color;
import android.util.Log;

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
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

/**
 * Created by cw on 2017/3/30.
 */

public class MonthChartMaker {
    private String[] strings;
    private double[] maxns;
    private double[] minns;
    private LineChart lineChart;

    public MonthChartMaker(String[] strings, double[] maxns, double[] minns, LineChart lineChart) {
        this.strings = strings;
        this.maxns = maxns;
        this.minns = minns;
        this.lineChart = lineChart;
    }



    public void makeChart(){

        if(MonthChartMaker.this.maxns!=null){
            LineData mLineData = getLineData();
            showChart(MonthChartMaker.this.lineChart, mLineData, Color.rgb(255, 255, 255));
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

        LimitLine yLimitLine = new LimitLine(1.8f,"超标线 1.8mg/m³");
        yLimitLine.setTextSize(15f);
        yLimitLine.setLineColor(Color.RED);
        yLimitLine.setTextColor(Color.RED);

        leftAxis.addLimitLine(yLimitLine);

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                return MonthChartMaker.this.strings[(int)value];
            }


        };

        //定制X轴起点和终点Label不能超出屏幕。
//        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);

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

        //总合集
        ArrayList<ILineDataSet> lineDataSets = new ArrayList<ILineDataSet>();

        // y轴的当天最大值数据
        ArrayList<Entry> yValuesMax = new ArrayList<Entry>();
        //System.out.println(RealChartMaker.this.doubles.length+ "  123121");
        if(MonthChartMaker.this.maxns!=null){
            for(int i = 0; i < MonthChartMaker.this.maxns.length ;i++){
                Entry y = new Entry(i, (float)maxns[i]) ;
                yValuesMax.add(y);

            }
        }

        // y轴的数据集合
        LineDataSet lineDataSet = new LineDataSet(yValuesMax, "当天最大值" /*显示在比例图上*/);

        //用y轴的集合来设置参数
        lineDataSet.setLineWidth(1.75f); // 线宽
        lineDataSet.setCircleSize(5f);// 显示的圆形大小
        lineDataSet.setColor(Color.rgb(153,0,0));// 显示颜色
        lineDataSet.setCircleColor(Color.rgb(153,0,0));// 圆形的颜色
//        lineDataSet.setHighLightColor(R.color.colorBule); // 高亮的线的颜色
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);//曲线风格
        lineDataSet.setCubicIntensity(0.2f);//设置曲线的平滑度
        lineDataSet.setDrawFilled(true);//设置允许填充

        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSets.add(lineDataSet); // add the datasets



        // y轴当天最小值的数据
        ArrayList<Entry> yValuesMin = new ArrayList<Entry>();
        //System.out.println(RealChartMaker.this.doubles.length+ "  123121");
        if(MonthChartMaker.this.minns!=null){
            for(int i = 0; i < MonthChartMaker.this.minns.length ;i++){
                Entry y = new Entry(i, (float)minns[i]) ;
                yValuesMin.add(y);
                Log.e("status",minns[i]+"");

            }
        }

        // y轴的数据集合
        LineDataSet lineDataSet2 = new LineDataSet(yValuesMin, "当天最小值" /*显示在比例图上*/);

        //用y轴的集合来设置参数
        lineDataSet2.setLineWidth(1.75f); // 线宽
        lineDataSet2.setCircleSize(5f);// 显示的圆形大小
        lineDataSet2.setColor(Color.rgb(0,0,153));// 显示颜色
        lineDataSet2.setCircleColor(Color.rgb(0,0,153));// 圆形的颜色
//        lineDataSet.setHighLightColor(R.color.colorBule); // 高亮的线的颜色
        lineDataSet2.setMode(LineDataSet.Mode.CUBIC_BEZIER);//曲线风格
        lineDataSet2.setCubicIntensity(0.2f);//设置曲线的平滑度
        lineDataSet2.setDrawFilled(true);//设置允许填充

        lineDataSet2.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSets.add(lineDataSet2); // add the datasets

        // create a data object with the datasets
        LineData lineData = new LineData(lineDataSets);

        return lineData;
    }
}
