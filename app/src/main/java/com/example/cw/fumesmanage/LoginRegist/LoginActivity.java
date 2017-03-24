package com.example.cw.fumesmanage.LoginRegist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.cw.fumesmanage.MainPage.MainActivity;
import com.example.cw.fumesmanage.R;
import com.example.cw.fumesmanage.Tools.SetBarColor;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //透明状态栏
        SetBarColor setBarColor = new SetBarColor();
        setBarColor.MakeBarTrans(LoginActivity.this);

        btnLogin = (Button)findViewById(R.id.id_BtnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }
}
