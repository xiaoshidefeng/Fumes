package com.example.cw.fumesmanage.MainPage.MainListview.DetailActivity.AbNormalFragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.cw.fumesmanage.R;
import com.example.cw.fumesmanage.Tools.ConstClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.cw.fumesmanage.Tools.NetWorkRealTime.dayTimes;
import static com.example.cw.fumesmanage.Tools.NetWorkRealTime.dayVals;

/**
 * Created by cw on 2017/3/24.
 */


public class AbNormalFragment extends Fragment {

    private List<AbNormalBean> itemBeen = new ArrayList<>();

    private ListView listView;

    private String id;

    private URL url;


    private String[] s = {"微软","alibaba","腾讯","Baidu","微软","alibaba","腾讯","Baidu","腾讯","Baidu"
            ,"微软","alibaba","腾讯","Baidu","微软","alibaba","腾讯","Baidu","腾讯","Baidu"};


    public AbNormalFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fg_abnormal_layout, container, false);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initview();

    }
    private void initview() {
        listView = (ListView)getActivity().findViewById(R.id.id_LVabnormal);

        itemBeen.clear();

        for(int i = 0;i < dayTimes.length; i++){
            if(dayVals[i] >= 1.8){
                itemBeen.add(new AbNormalBean(i, dayTimes[i], dayVals[i]));
                System.out.print(dayTimes[i] + "1231231" + dayVals[i]);

            }

        }

        SharedPreferences vals = getActivity().getSharedPreferences("EnterInfo", 0);
        id = vals.getString("id","");
        try {
            url = new URL(ConstClass.GET_REALTIME_ENTERPRISES_VALUE+id);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

//        NetWorkAbnormal netWorkAbnormal = new NetWorkAbnormal(itemBeen, listView, url, getContext());
//
//        netWorkAbnormal.initEnterprises();

        AbNormalAdapter myAdapter = new AbNormalAdapter(getContext(),itemBeen);
        listView.setAdapter(myAdapter);
    }

}
