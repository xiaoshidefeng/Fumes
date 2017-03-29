package com.example.cw.fumesmanage.Tools;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;

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

public class NetWorkMap {
    private URL url;
    private List<LatLng> listLatLng = new ArrayList<>();
    private AMap aMap;
    private Context context;

    public NetWorkMap(URL url, List<LatLng> listLatLng, AMap aMap, Context context) {
        this.url = url;
        this.listLatLng = listLatLng;
        this.aMap = aMap;
        this.context = context;
    }



    /**
     * 功能 更新获取所有企业位置数据ui
     */
    public void handler1(){
        handler.sendEmptyMessage(1);//此处发送消息给handler,然后handler接收消息并处理消息进而更新ui
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            for (LatLng l: NetWorkMap.this.listLatLng) {
                Marker marker = NetWorkMap.this.aMap.addMarker(new MarkerOptions()
                        .position(l).title("title").snippet("message"));
            }

        }
    };


    /**
     * 功能 获取所有企业坐标
     * 方法 GET
     */
    public void enterprises(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection connection = null;
                try {

                    URL url  = NetWorkMap.this.url;

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

                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject oneEnterPrises = jsonArray.getJSONObject(i);

                        int id = oneEnterPrises.getInt("id");
                        String enter_long = oneEnterPrises.getString("enterprise_long");
                        String name = oneEnterPrises.getString("name");
                        String province = oneEnterPrises.getString("province");
                        String city = oneEnterPrises.getString("city");
                        String area = oneEnterPrises.getString("area");
                        float fx = (float) oneEnterPrises.getDouble("lng");
                        float fy = (float) oneEnterPrises.getDouble("lat");
                        float fval = (float) oneEnterPrises.getDouble("concentration");
                        int hood_id = oneEnterPrises.getInt("hood_id");
                        String created_at = oneEnterPrises.getString("created_at");
                        String updated_at = oneEnterPrises.getString("updated_at");

                        NetWorkMap.this.listLatLng.add(new LatLng(fx,fy));
                    }

                    handler1();

                    //TODO

                }   catch (Exception e) {
                    Log.e("errss", e.getMessage());
                }
            }
        }).start();
    }

}
