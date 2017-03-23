package com.example.cw.fumesmanage.MainPage.MainListview.DetailActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.cw.fumesmanage.R;
import com.example.cw.fumesmanage.SetBarColor;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //透明状态栏
        SetBarColor setBarColor = new SetBarColor();
        setBarColor.MakeBarTrans(DetailActivity.this);

    }
}
