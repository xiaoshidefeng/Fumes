package com.example.cw.fumesmanage.MainPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cw.fumesmanage.R;

/**
 * Created by cw on 2017/3/22.
 */

public class ListFragment extends android.support.v4.app.Fragment {
    public ListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fg_content, container, false);
        TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
        txt_content.setText("第二个Fragment");
       //Log.e("HEHE", "1日狗");
        return view;
    }
}
