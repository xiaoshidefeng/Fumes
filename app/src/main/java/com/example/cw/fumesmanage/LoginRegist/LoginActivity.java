package com.example.cw.fumesmanage.LoginRegist;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cw.fumesmanage.MainPage.MainActivity;
import com.example.cw.fumesmanage.R;
import com.example.cw.fumesmanage.Tools.SetBarColor;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;

    private EditText etUsername;

    private EditText etPassWord;

    private String name;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //透明状态栏
        SetBarColor setBarColor = new SetBarColor();
        setBarColor.MakeBarTrans(LoginActivity.this);

        etUsername = (EditText)findViewById(R.id.id_ETusername);
        etPassWord = (EditText)findViewById(R.id.id_ETpassword);

        btnLogin = (Button)findViewById(R.id.id_BtnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    }

    private void showDi(){
        final ProgressDialog dialog = ProgressDialog.show(
                this, "正在登录...", "请耐心等待...");// 之所以用final定义，主要目的是为了让内部类可以访问方法中定义的参数。
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(3000);

                    //开启ui线程
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this,"上传完成",Toast.LENGTH_SHORT).show();

                        }
                    });

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    dialog.dismiss();

                }

            };
        }.start();
    }
}
