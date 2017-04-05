package com.example.cw.fumesmanage.Tools;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;

import com.example.cw.fumesmanage.MainPage.MainListview.DetailActivity.Monthly.MonthAdapter;
import com.example.cw.fumesmanage.MainPage.MainListview.DetailActivity.Monthly.MonthListBean;
import com.github.mikephil.charting.charts.LineChart;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by cw on 2017/3/30.
 */

public class NetWorkMonth {

    private Context context;
    private String id;
    private ListView listView;
    private LineChart lineChart;
    private List<MonthListBean> monthListBeanList = new ArrayList<>();

    private double[] maxxDoubles;
    private double[] minnDoubles;
    private String[] times;

    JSONArray jsonArray;

    public NetWorkMonth(Context context, String id, ListView listView
            , LineChart lineChart, List<MonthListBean> monthListBeanList) {
        this.context = context;
        this.id = id;
        this.listView = listView;
        this.lineChart = lineChart;
        this.monthListBeanList = monthListBeanList;
    }



    /**
     * 功能 更新获取企业实时数据ui
     */
    public void handler5(){
        handler.sendEmptyMessage(1);//此处发送消息给handler,然后handler接收消息并处理消息进而更新ui
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(jsonArray.length()!=0){
                MonthAdapter myAdapter = new MonthAdapter(NetWorkMonth.this.context,NetWorkMonth.this.monthListBeanList);
                NetWorkMonth.this.listView.setAdapter(myAdapter);

//            RealChartMaker realChartMaker = new RealChartMaker(times, vals, NetWorkRealTime.this.lineChart);
//            realChartMaker.makeChart();
                MonthChartMaker monthChartMaker = new MonthChartMaker(times, maxxDoubles, minnDoubles, NetWorkMonth.this.lineChart);
                monthChartMaker.makeChart();
            }

        }
    };

    public void getMonth() {
        //开启子线程访问网络 回复帖子模块
        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection connection = null;
                try {

                    Calendar c = Calendar.getInstance();
                    String year = String.valueOf(c.get(Calendar.YEAR));

                    //暂时改成3月份 4月还没有数据
                    String month = String.valueOf(c.get(Calendar.MONTH));

//                    String month = String.valueOf(c.get(Calendar.MONTH)+1);
                    URL url = new URL(ConstClass.MONTH_CHART_VALUE);


                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("POST");

                    //连接超时设置
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    //设置运行输入,输出:
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    //Post方式不能缓存,需手动设置为false
                    connection.setUseCaches(false);
                    //2设置http请求数据的类型为表单类型

//                    connection.setRequestProperty("Content-type","application/x-www-form-urlencoded");

                    String data = "id=" + URLEncoder.encode(NetWorkMonth.this.id,"utf-8") + "&date=" +
                            URLEncoder.encode(year + "-" +month,"utf-8");
                    OutputStream out = connection.getOutputStream();
                    out.write(data.getBytes());
                    out.flush();

                    if (connection.getResponseCode() == 200) {
                        // 获取响应的输入流对象
                        InputStream is = connection.getInputStream();
                        //对获取的流进行读取
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"));
                        final StringBuilder response = new StringBuilder();
                        String line=null;
                        while ((line=reader.readLine())!=null){
                            response.append(line);
                        }

                        jsonArray =new JSONArray(response.toString());

                        maxxDoubles = new double[jsonArray.length()];
                        minnDoubles = new double[jsonArray.length()];
                        times = new String[jsonArray.length()];

                        for(int i = 0,count = 0; i < jsonArray.length(); i++, count++){
                            JSONObject oneEnterPrises = jsonArray.getJSONObject(i);

                            int id = oneEnterPrises.getInt("id");
                            String time = oneEnterPrises.getString("time");
                            Double maxVal = oneEnterPrises.getDouble("maxn");
                            Double minVal = oneEnterPrises.getDouble("minn");
                            String created_at = oneEnterPrises.getString("created_at");
                            String updated_at = oneEnterPrises.getString("updated_at");
                            //Log.e("error", count+ "");
                            times[count] = time;
                            maxxDoubles[count] = maxVal;
                            minnDoubles[count] = minVal;
                            NetWorkMonth.this.monthListBeanList.add(new MonthListBean(
                                    id,
                                    time,
                                    created_at,
                                    updated_at,
                                    maxVal,
                                    minVal
                            ));
                        }

                        handler5();

                        // 释放资源
                        is.close();


                    }

                    else{


                    }


                }   catch (Exception e) {

                    Log.e("error", e.getMessage());
                }
            }
        }).start();
    }

}
