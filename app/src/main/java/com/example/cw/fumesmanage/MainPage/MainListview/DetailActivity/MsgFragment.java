package com.example.cw.fumesmanage.MainPage.MainListview.DetailActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cw.fumesmanage.R;

/**
 * Created by cw on 2017/3/24.
 */

public class MsgFragment extends Fragment {

    private TextView tvName;

    private TextView tvValue;

    public MsgFragment(){

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();

    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fg_msg_layout, container, false);

        return view;
    }

    private void initView() {
        tvName = (TextView)getActivity().findViewById(R.id.id_TvName);
        tvValue = (TextView)getActivity().findViewById(R.id.id_TvValue);

//        //1、获取Preferences
//        SharedPreferences vals = getActivity().getSharedPreferences("postInfo", 0);
//        //2、取出数据
//        String name = vals.getString("title","");
//        String values = vals.getString("value","");
//
//        System.out.println(name + values+"123456");


//        tvName.setText(name);
//        tvValue.setText(values);

    }
}
