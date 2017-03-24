package com.example.cw.fumesmanage.Tools;

import android.app.Activity;
import android.os.Build;
import android.view.WindowManager;

/**
 * Created by cw on 2017/3/20.
 */

public class SetBarColor {

    public void MakeBarTrans(Activity activity){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }


}

