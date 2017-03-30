package com.example.cw.fumesmanage.MainPage.MainListview.DetailActivity.Monthly;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.cw.fumesmanage.R;
import com.example.cw.fumesmanage.Tools.NetWorkMonth;
import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cw on 2017/3/24.
 */

public class MonthListFragment extends Fragment {
    private List<MonthListBean> itemBeen = new ArrayList<>();

    private ListView listView;

    private LineChart mLineChart;

    private String id;

    private String[] s = {"微软","alibaba","腾讯","Baidu","微软","alibaba","腾讯","Baidu","腾讯","Baidu"
            ,"微软","alibaba","腾讯","Baidu","微软","alibaba","腾讯","Baidu","腾讯","Baidu"};


    public MonthListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fg_month_listchart_layout, container, false);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initview();

    }
    private void initview() {
        listView = (ListView)getActivity().findViewById(R.id.id_LVmonth);
        mLineChart = (LineChart)getActivity().findViewById(R.id.id_Monthchart);

        SharedPreferences vals = getActivity().getSharedPreferences("EnterInfo", 0);
        id = vals.getString("id","");

        itemBeen.clear();
        NetWorkMonth netWorkMonth = new NetWorkMonth(getContext(), id, listView, mLineChart, itemBeen);
        netWorkMonth.getMonth();


    }


}
