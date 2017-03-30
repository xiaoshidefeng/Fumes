package com.example.cw.fumesmanage.MainPage.MainListview.DetailActivity.RealTime;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.cw.fumesmanage.R;
import com.example.cw.fumesmanage.Tools.ChartDataMaker;
import com.example.cw.fumesmanage.Tools.ConstClass;
import com.example.cw.fumesmanage.Tools.NetWorkRealTime;
import com.github.mikephil.charting.charts.LineChart;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cw on 2017/3/24.
 */

public class DayListFragment extends Fragment {
    private List<RealTimeBean> itemBeen = new ArrayList<>();

    private ListView listView;

    private URL url;

    private String id;

    private LineChart mLineChart;


    private String[] s = {"微软","alibaba","腾讯","Baidu","微软","alibaba","腾讯","Baidu","腾讯","Baidu"
            ,"微软","alibaba","腾讯","Baidu","微软","alibaba","腾讯","Baidu","腾讯","Baidu"};


    public DayListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fg_day_listchart_layout, container, false);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initview();

    }
    private void initview() {
        listView = (ListView)getActivity().findViewById(R.id.id_LVday);

        SharedPreferences vals = getActivity().getSharedPreferences("EnterInfo", 0);
        id = vals.getString("id","");
        try {
            url = new URL(ConstClass.GET_REALTIME_ENTERPRISES_VALUE+id);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        ChartDataMaker cdm = new ChartDataMaker();
        mLineChart = (LineChart)getActivity().findViewById(R.id.id_Daychart);
        NetWorkRealTime netWorkRealTime = new NetWorkRealTime(itemBeen, listView, url, getContext(), mLineChart);
        netWorkRealTime.enterprises();

        chartMake();

    }

    private void chartMake() {

    }

}
