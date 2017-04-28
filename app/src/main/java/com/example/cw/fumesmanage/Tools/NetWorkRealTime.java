package com.example.cw.fumesmanage.Tools;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cw.fumesmanage.MainPage.MainListview.DetailActivity.RealTime.RealTimeAdapter;
import com.example.cw.fumesmanage.MainPage.MainListview.DetailActivity.RealTime.RealTimeBean;
import com.github.mikephil.charting.charts.LineChart;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cw on 2017/3/29.
 */

public class NetWorkRealTime {

    private List<RealTimeBean> realTimeBeanList = new ArrayList<>();

    private ListView listView;

    private URL url;

    private Context context;

    private LineChart lineChart;

    private SwipeRefreshLayout swipeRefreshLayout;

    public static String[] dayTimes;

    public static double[] dayVals;

    private JSONArray jsonArray;

    public NetWorkRealTime(List<RealTimeBean> realTimeBeanList
            , ListView listView, URL url, Context context, LineChart lineChart, SwipeRefreshLayout swipeRefreshLayout) {
        this.realTimeBeanList = realTimeBeanList;
        this.listView = listView;
        this.url = url;
        this.context = context;
        this.lineChart = lineChart;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }


    /**
     * 功能 更新获取企业实时数据ui init
     */
    public void handler4(){
        handler.sendEmptyMessage(1);//此处发送消息给handler,然后handler接收消息并处理消息进而更新ui
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(jsonArray!=null){
                RealTimeAdapter myAdapter = new RealTimeAdapter(NetWorkRealTime.this.context,NetWorkRealTime.this.realTimeBeanList);
                NetWorkRealTime.this.listView.setAdapter(myAdapter);

                RealChartMaker realChartMaker = new RealChartMaker(dayTimes, dayVals, NetWorkRealTime.this.lineChart);
                realChartMaker.makeChart();
            }

        }
    };


    /**
     * 功能 更新获取企业实时数据ui refresh
     */
    public void handler5(){
        handler2.sendEmptyMessage(1);//此处发送消息给handler,然后handler接收消息并处理消息进而更新ui
    }
    private Handler handler2 = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            NetWorkRealTime.this.swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(NetWorkRealTime.this.context,"刷新完成",Toast.LENGTH_SHORT).show();
            if(jsonArray!=null){
                RealTimeAdapter myAdapter = new RealTimeAdapter(NetWorkRealTime.this.context,NetWorkRealTime.this.realTimeBeanList);
                NetWorkRealTime.this.listView.setAdapter(myAdapter);
                RealChartMaker realChartMaker = new RealChartMaker(dayTimes, dayVals, NetWorkRealTime.this.lineChart);
                realChartMaker.makeChart();
            }

        }
    };

    /**
     * 功能 获取企业实时数据 init
     * 方法 GET
     */
    public void initEnterprises(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection connection = null;
                try {

                    URL url  = NetWorkRealTime.this.url;

                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();

                    //连接超时设置
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);

                    //获取输入流
                    InputStream in = connection.getInputStream();

                    //对获取的流进行读取
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in,"utf-8"));
                    final StringBuilder response = new StringBuilder();
                    String line=null;
                    while ((line=reader.readLine())!=null){
                        response.append(line);
                    }


                    jsonArray = new JSONArray(response.toString());

                    dayTimes = new String[jsonArray.length()];
                    dayVals = new double[jsonArray.length()];

                    for(int i = 0,count = 0; i < jsonArray.length(); i++,count++){
                        JSONObject oneEnterPrises = jsonArray.getJSONObject(i);

                        int id = oneEnterPrises.getInt("id");
                        String time = oneEnterPrises.getString("time");
                        Double fval = oneEnterPrises.getDouble("concentration");
                        int hood_id = oneEnterPrises.getInt("hood_id");
                        String created_at = oneEnterPrises.getString("created_at");
                        String updated_at = oneEnterPrises.getString("updated_at");
                        dayTimes[count] = time;
                        dayVals[count] = fval;
                        NetWorkRealTime.this.realTimeBeanList.add(new RealTimeBean(
                                id,
                                hood_id,
                                fval,
                                time,
                                created_at,
                                updated_at
                        ));
                    }
                    handler4();


                }   catch (Exception e) {
                    Log.e("errss", e.getMessage());
                }
            }
        }).start();
    }


    /**
     * 功能 获取企业实时数据 refresh
     * 方法 GET
     */
    public void refreshEnterprises(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection connection = null;
                try {

                    URL url  = NetWorkRealTime.this.url;

                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();

                    //连接超时设置
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);

                    //获取输入流
                    InputStream in = connection.getInputStream();

                    //对获取的流进行读取
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in,"utf-8"));
                    final StringBuilder response = new StringBuilder();
                    String line=null;
                    while ((line=reader.readLine())!=null){
                        response.append(line);
                    }


                    jsonArray = new JSONArray(response.toString());

                    dayTimes = new String[jsonArray.length()];
                    dayVals = new double[jsonArray.length()];

                    for(int i = 0,count = 0; i < jsonArray.length(); i++,count++){
                        JSONObject oneEnterPrises = jsonArray.getJSONObject(i);

                        int id = oneEnterPrises.getInt("id");
                        String time = oneEnterPrises.getString("time");
                        Double fval = oneEnterPrises.getDouble("concentration");
                        int hood_id = oneEnterPrises.getInt("hood_id");
                        String created_at = oneEnterPrises.getString("created_at");
                        String updated_at = oneEnterPrises.getString("updated_at");
                        dayTimes[count] = time;
                        dayVals[count] = fval;
                        NetWorkRealTime.this.realTimeBeanList.add(new RealTimeBean(
                                id,
                                hood_id,
                                fval,
                                time,
                                created_at,
                                updated_at
                        ));
                    }
                    handler5();


                }   catch (Exception e) {
                    Log.e("errss", e.getMessage());
                }
            }
        }).start();
    }



}
