package com.example.cw.fumesmanage.MainPage.MainListview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.cw.fumesmanage.R;
import com.example.cw.fumesmanage.Tools.NetWorkGo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cw on 2017/3/22.
 */

public class ListFragment extends android.support.v4.app.Fragment {

    private List<MainItemBean> itemBeen = new ArrayList<>();

    private ListView listView;

    private URL url;

    private String[] s = {"微软","alibaba","腾讯","Baidu","微软","alibaba","腾讯","Baidu","腾讯","Baidu"
        ,"微软","alibaba","腾讯","Baidu","微软","alibaba","腾讯","Baidu","腾讯","Baidu"};


    public ListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_list_layout, container, false);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initview();

    }
    private void initview() {
        listView = (ListView)getActivity().findViewById(R.id.id_MainListView);

//        for(int i = 0;i<20 ;i++){
//            itemBeen.add(new MainItemBean(
//                    i,
//                    s[i],
//                    i+"mg/m³"
//            ));
//        }

        String s = "http://120.25.90.170/enterprises";
        try {
            url = new URL(s);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        NetWorkGo netWorkGo = new NetWorkGo(url,itemBeen,listView,getContext());

        netWorkGo.enterprises();



//        MainAdapter myAdapter = new MainAdapter(getContext(),itemBeen);
//        listView.setAdapter(myAdapter);
    }



}
