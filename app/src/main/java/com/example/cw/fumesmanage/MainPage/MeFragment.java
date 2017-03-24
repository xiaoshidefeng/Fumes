package com.example.cw.fumesmanage.MainPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cw.fumesmanage.R;

/**
 * Created by cw on 2017/3/22.
 */

public class MeFragment extends android.support.v4.app.Fragment {
    public MeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fg_me_layout, container, false);

        //Log.e("HEHE", "1日狗");
        return view;
    }
}
