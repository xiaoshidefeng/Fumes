package com.example.cw.fumesmanage.Tools;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by cw on 2017/3/27.
 */

public class NetWorkGo {

    private URL url;


    public NetWorkGo(URL url) {
        this.url = url;
    }

    /**
     * 功能 获取所有企业数据
     * 方法 GET
     */
    public void enterprises(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection connection = null;
                try {

                    URL url  = NetWorkGo.this.url;

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

//                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray jsonArray = new JSONArray(response.toString());

                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject oneEnterPrises = jsonArray.getJSONObject(i);

                        int id = oneEnterPrises.getInt("id");
                        String enter_long = oneEnterPrises.getString("enterprise_long");
                        String name = oneEnterPrises.getString("name");
                        String province = oneEnterPrises.getString("city");
                        String area = oneEnterPrises.getString("area");
                        float fx = (float) oneEnterPrises.getDouble("lng");
                        float fy = (float) oneEnterPrises.getDouble("lat");
                        float fval = (float) oneEnterPrises.getDouble("concentration");
                        int hood_id = oneEnterPrises.getInt("hood_id");
                        String created_at = oneEnterPrises.getString("created_at");
                        String updated_at = oneEnterPrises.getString("updated_at");

                    }

                    //TODO

                }   catch (Exception e) {
                    Log.e("errss", e.getMessage());
                }
            }
        }).start();
    }


    /**
     * 功能 获取企业实时数据
     * 方法 GET
     */
    public void getRealTimeDate(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection connection = null;
                try {

                    URL url  = NetWorkGo.this.url;

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

//                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray jsonArray = new JSONArray(response.toString());

                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject oneEnterPrisesDeatil = jsonArray.getJSONObject(i);

                        int id = oneEnterPrisesDeatil.getInt("id");
                        float fval = (float) oneEnterPrisesDeatil.getDouble("concentration");
                        int hood_id = oneEnterPrisesDeatil.getInt("hood_id");
                        String time = oneEnterPrisesDeatil.getString("time");
                        String created_at = oneEnterPrisesDeatil.getString("created_at");
                        String updated_at = oneEnterPrisesDeatil.getString("updated_at");

                    }
                    //TODO



                }   catch (Exception e) {
                    Log.e("errss", e.getMessage());
                }
            }
        }).start();
    }

}
