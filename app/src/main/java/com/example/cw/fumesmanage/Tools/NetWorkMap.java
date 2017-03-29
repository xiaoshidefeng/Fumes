package com.example.cw.fumesmanage.Tools;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.example.cw.fumesmanage.R;

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
    private List<MapBean> listMapBeanList = new ArrayList<>();
    private AMap aMap;
    private Context context;
    private JSONArray jsonArray;

    public NetWorkMap(URL url, List<MapBean> listMapBeanList, AMap aMap, Context context) {
        this.url = url;
        this.listMapBeanList = listMapBeanList;
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

            for (MapBean m: NetWorkMap.this.listMapBeanList) {
                LatLng l = m.latLng;
                if(m.value<2.0){
                    Marker marker = NetWorkMap.this.aMap.addMarker(new MarkerOptions()
                            .position(l).title(m.id + "").snippet("message")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.green2d)));
                }else {
                    Marker marker = NetWorkMap.this.aMap.addMarker(new MarkerOptions()
                            .position(l).title(m.id + "").snippet("message")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.red2d)));
                }

            }

            //设置中心点和缩放比例
            NetWorkMap.this.aMap.moveCamera(CameraUpdateFactory.changeLatLng(
                    NetWorkMap.this.listMapBeanList.get(jsonArray.length()-1).latLng));
            NetWorkMap.this.aMap.moveCamera(CameraUpdateFactory.zoomTo(15));
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

                    jsonArray = new JSONArray(response.toString());

                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject oneEnterPrises = jsonArray.getJSONObject(i);

                        int id = oneEnterPrises.getInt("id");
                        String enter_long = oneEnterPrises.getString("enterprise_long");
                        String name = oneEnterPrises.getString("name");
                        String province = oneEnterPrises.getString("province");
                        String city = oneEnterPrises.getString("city");
                        String area = oneEnterPrises.getString("area");
                        Double fx =  oneEnterPrises.getDouble("lng");
                        Double fy =  oneEnterPrises.getDouble("lat");
                        Double fval =  oneEnterPrises.getDouble("concentration");
                        int hood_id = oneEnterPrises.getInt("hood_id");
                        String created_at = oneEnterPrises.getString("created_at");
                        String updated_at = oneEnterPrises.getString("updated_at");

//                        Log.e("errss", fx+ "  "+ fy);
                        NetWorkMap.this.listMapBeanList.add(new MapBean(
                                new LatLng(fy,fx),
                                fval,
                                1,
                                id
                        ));
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
