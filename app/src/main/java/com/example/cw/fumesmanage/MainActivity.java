package com.example.cw.fumesmanage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //透明状态栏
        SetBarColor setBarColor = new SetBarColor();
        setBarColor.MakeBarTrans(MainActivity.this);




    }
}
