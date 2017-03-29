package com.example.cw.fumesmanage.Tools;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;

import com.example.cw.fumesmanage.MainPage.MainListview.MainAdapter;
import com.example.cw.fumesmanage.MainPage.MainListview.MainItemBean;

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
 * Created by cw on 2017/3/27.
 */

public class NetWorkGo {

    private URL url;
    private List<MainItemBean> listMainItemBean = new ArrayList<>();
    private ListView listView;
    private Context context;

    public NetWorkGo(URL url, List<MainItemBean> listMainItemBean, ListView listView, Context context) {
        this.url = url;
        this.listMainItemBean = listMainItemBean;
        this.listView = listView;
        this.context = context;
    }



    /**
     * 功能 更新获取所有企业数据ui
     */
    public void handler2(){
        handler.sendEmptyMessage(1);//此处发送消息给handler,然后handler接收消息并处理消息进而更新ui
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            MainAdapter myAdapter = new MainAdapter(NetWorkGo.this.context,NetWorkGo.this.listMainItemBean);
            listView.setAdapter(myAdapter);
        }
    };


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

                        NetWorkGo.this.listMainItemBean.add(new MainItemBean(
                                id,
                                enter_long,
                                name,
                                province,
                                city,
                                area,
                                fx,
                                fy,
                                fval,
                                hood_id,
                                created_at,
                                updated_at
                        ));
                    }

                    handler2();

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
