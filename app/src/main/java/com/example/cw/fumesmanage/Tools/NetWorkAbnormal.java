package com.example.cw.fumesmanage.Tools;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;

import com.example.cw.fumesmanage.MainPage.MainListview.DetailActivity.AbNormalFragment.AbNormalAdapter;
import com.example.cw.fumesmanage.MainPage.MainListview.DetailActivity.AbNormalFragment.AbNormalBean;

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
 * Created by cw on 2017/3/31.
 */

public class NetWorkAbnormal {

    private List<AbNormalBean> abNormalBeanList = new ArrayList<>();

    private ListView listView;

    private URL url;

    private Context context;

    public static String[] dayTimes;

    public static double[] dayVals;

    public NetWorkAbnormal(List<AbNormalBean> abNormalBeanList
            , ListView listView, URL url, Context context) {
        this.abNormalBeanList = abNormalBeanList;
        this.listView = listView;
        this.url = url;
        this.context = context;
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
            AbNormalAdapter myAdapter = new AbNormalAdapter(NetWorkAbnormal.this.context,NetWorkAbnormal.this.abNormalBeanList);
            NetWorkAbnormal.this.listView.setAdapter(myAdapter);

        }
    };


    /**
     * 功能 更新获取企业实时数据ui refresh
     */
//    public void handler5(){
//        handler2.sendEmptyMessage(1);//此处发送消息给handler,然后handler接收消息并处理消息进而更新ui
//    }
//    private Handler handler2 = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            RealTimeAdapter myAdapter = new RealTimeAdapter(NetWorkAbnormal.this.context,NetWorkRealTime.this.realTimeBeanList);
//            NetWorkAbnormal.this.listView.setAdapter(myAdapter);
//            NetWorkAbnormal.this.swipeRefreshLayout.setRefreshing(false);
//            Toast.makeText(NetWorkAbnormal.this.context,"刷新完成",Toast.LENGTH_SHORT).show();
//            RealChartMaker realChartMaker = new RealChartMaker(dayTimes, dayVals, NetWorkAbnormal.this.lineChart);
//            realChartMaker.makeChart();
//        }
//    };

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

                    URL url  = NetWorkAbnormal.this.url;

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


                    JSONArray jsonArray = new JSONArray(response.toString());

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
                        NetWorkAbnormal.this.abNormalBeanList.add(new AbNormalBean(
                                id,
                                time,
                                fval
                        ));
                    }
                    handler4();


                }   catch (Exception e) {
                    Log.e("errss", e.getMessage());
                }
            }
        }).start();
    }

}
