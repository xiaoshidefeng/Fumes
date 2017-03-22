package com.example.cw.fumesmanage;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;

    private MyFragmentPagerAdapter mAdapter;

    private RadioGroup rg_tab_bar;
    private RadioButton rb_map;
    private RadioButton rb_list;
    private RadioButton rb_me;

    private ViewPager vpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //透明状态栏
        SetBarColor setBarColor = new SetBarColor();
        setBarColor.MakeBarTrans(MainActivity.this);

        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());

        initView();
        rb_map.setChecked(true);

    }

    private void initView() {

            rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
            rb_map = (RadioButton) findViewById(R.id.rb_map);
            rb_list = (RadioButton) findViewById(R.id.rb_list);
            rb_me = (RadioButton) findViewById(R.id.rb_me);


            rg_tab_bar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    switch (i) {
                        case R.id.rb_map:
                            vpager.setCurrentItem(PAGE_ONE);
                            break;
                        case R.id.rb_list:
                            vpager.setCurrentItem(PAGE_TWO);
                            break;
                        case R.id.rb_me:
                            vpager.setCurrentItem(PAGE_THREE);
                            break;

                    }
                }
            });


            vpager = (ViewPager) findViewById(R.id.vpager);
            vpager.setAdapter(mAdapter);
            vpager.setCurrentItem(0);
            vpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
                    if (state == 2) {
                        switch (vpager.getCurrentItem()) {
                            case PAGE_ONE:
                                rb_map.setChecked(true);
                                break;
                            case PAGE_TWO:
                                rb_list.setChecked(true);
                                break;
                            case PAGE_THREE:
                                rb_me.setChecked(true);
                                break;

                        }
                    }

                }
            });
    }


}
