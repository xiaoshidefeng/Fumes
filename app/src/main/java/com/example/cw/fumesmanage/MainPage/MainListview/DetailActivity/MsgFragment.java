package com.example.cw.fumesmanage.MainPage.MainListview.DetailActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cw.fumesmanage.R;

/**
 * Created by cw on 2017/3/24.
 */

public class MsgFragment extends Fragment {
    public MsgFragment(){

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fg_msg_layout, container, false);



        return view;
    }
}
