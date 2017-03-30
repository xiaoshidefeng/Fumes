package com.example.cw.fumesmanage.MainPage.MainListview.DetailActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cw.fumesmanage.R;
import com.example.cw.fumesmanage.Tools.ConstClass;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by cw on 2017/3/24.
 */

public class MsgFragment extends Fragment {

    private TextView TvCode;
    private TextView TvName;
    private TextView TvTime;
    private TextView TvValue;
    private TextView TvHoodStatus;
    private TextView TVSeeStatus;
    private TextView TVOpenTime;
    private TextView TVLinkStatus;
    private TextView TvArea;

    private String EnterArea;
    private String Id;
    private int id;
    private String enter_long;
    private String name;
    private String province;
    private String city;
    private String area;
    private Double fx;
    private Double fy;
    private Double fval;
    private int hood_id;
    private String created_at;
    private String updated_at;

    private ProgressBar progressBar;

    public MsgFragment(){

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();

        enterprises();


    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fg_msg_layout, container, false);

        return view;
    }

    private void initView() {
        TvCode = (TextView)getActivity().findViewById(R.id.id_TVcodeNumer);
        TvName = (TextView)getActivity().findViewById(R.id.id_TvName);
        TvTime = (TextView)getActivity().findViewById(R.id.id_TVTime);
        TvValue = (TextView)getActivity().findViewById(R.id.id_TvValue);
        TvHoodStatus = (TextView)getActivity().findViewById(R.id.id_TVFumesStatus);
        TVSeeStatus = (TextView)getActivity().findViewById(R.id.id_TVSeeStatus);
        TVOpenTime = (TextView)getActivity().findViewById(R.id.id_TVOpenTime);
        TVLinkStatus = (TextView)getActivity().findViewById(R.id.id_TVContactStatus);
        TvArea = (TextView)getActivity().findViewById(R.id.id_TvArea);
        progressBar = (ProgressBar)getActivity().findViewById(R.id.id_ProgressBarDetail);
        SharedPreferences vals = getActivity().getSharedPreferences("EnterInfo", 0);
        Id = vals.getString("id","");

        progressBar.setVisibility(View.VISIBLE);

    }

    /**
     * 功能 更新获取企业数据ui
     */
    public void handler3(){
        handler.sendEmptyMessage(1);//此处发送消息给handler,然后handler接收消息并处理消息进而更新ui
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            TvCode.setText(enter_long);
            TvName.setText(name);
            TvTime.setText(updated_at);
            TvValue.setText(fval+"mg/m³");
            TvHoodStatus.setText("正常");
            TVSeeStatus.setText("正常");
            TVOpenTime.setText(created_at);
            TVLinkStatus.setText("正常");
            TvArea.setText(province+city+area);
            progressBar.setVisibility(View.GONE);
        }
    };


    /**
     * 功能 获取所一个业数据
     * 方法 GET
     */
    public void enterprises(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection connection = null;
                try {

                    URL url  = new URL(ConstClass.GET_ONE_ENTERPRISES+Id);

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

                    JSONObject oneEnterPrises = new JSONObject(response.toString());

                    id = oneEnterPrises.getInt("id");
                    enter_long = oneEnterPrises.getString("enterprise_long");
                    name = oneEnterPrises.getString("name");
                    province = oneEnterPrises.getString("province");
                    city = oneEnterPrises.getString("city");
                    area = oneEnterPrises.getString("area");
                    fx =  oneEnterPrises.getDouble("lng");
                    fy =  oneEnterPrises.getDouble("lat");
                    fval =  oneEnterPrises.getDouble("concentration");
                    hood_id = oneEnterPrises.getInt("hood_id");
                    created_at = oneEnterPrises.getString("created_at");
                    updated_at = oneEnterPrises.getString("updated_at");

                    handler3();

                    //TODO

                }   catch (Exception e) {
                    Log.e("errss", e.getMessage());
                }
            }
        }).start();
    }




}
