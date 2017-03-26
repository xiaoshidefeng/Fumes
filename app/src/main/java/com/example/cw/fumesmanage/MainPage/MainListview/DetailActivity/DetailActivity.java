package com.example.cw.fumesmanage.MainPage.MainListview.DetailActivity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.cw.fumesmanage.R;
import com.example.cw.fumesmanage.Tools.SetBarColor;

public class DetailActivity extends AppCompatActivity {

    //几个代表页面的常量
    public static final int D_PAGE_ONE = 0;
    public static final int D_PAGE_TWO = 1;
    public static final int D_PAGE_THREE = 2;
    public static final int D_PAGE_FOUR = 3;
    public static final int D_PAGE_FIVE = 4;

    private DetailPagerAdapter mAdapter;

    private RadioGroup rgTabBar;
    private RadioButton rbMsg;
    private RadioButton rbDayListChart;
    private RadioButton rbMonthListChart;
    private RadioButton rbUnNormal;
    private RadioButton rbOnSite;

    private ImageView imgBack;

    private ViewPager vpager;

    private TextView TvTopBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //透明状态栏
        SetBarColor setBarColor = new SetBarColor();
        setBarColor.MakeBarTrans(DetailActivity.this);

        mAdapter = new DetailPagerAdapter(getSupportFragmentManager());

        initView();
        rbMsg.setChecked(true);

    }

    private void initView() {
        TvTopBar = (TextView)findViewById(R.id.id_detail_txt_topbar);
        rgTabBar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rbMsg = (RadioButton)findViewById(R.id.rb_msg);
        rbDayListChart = (RadioButton)findViewById(R.id.rb_day);
        rbMonthListChart = (RadioButton)findViewById(R.id.rb_month);
        rbUnNormal = (RadioButton)findViewById(R.id.rb_abnormal);
        rbOnSite = (RadioButton)findViewById(R.id.rb_onstie);
        imgBack = (ImageView)findViewById(R.id.id_back);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rgTabBar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_msg:
                        vpager.setCurrentItem(D_PAGE_ONE);
                        break;
                    case R.id.rb_day:
                        vpager.setCurrentItem(D_PAGE_TWO);
                        break;
                    case R.id.rb_month:
                        vpager.setCurrentItem(D_PAGE_THREE);
                        break;
                    case R.id.rb_abnormal:
                        vpager.setCurrentItem(D_PAGE_FOUR);
                        break;
                    case R.id.rb_onstie:
                        vpager.setCurrentItem(D_PAGE_FIVE);
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
                        case D_PAGE_ONE:
                            rbMsg.setChecked(true);
                            TvTopBar.setText("信息查看");
                            break;
                        case D_PAGE_TWO:
                            rbDayListChart.setChecked(true);
                            TvTopBar.setText("当天信息");
                            break;
                        case D_PAGE_THREE:
                            rbMonthListChart.setChecked(true);
                            TvTopBar.setText("当月信息");
                            break;
                        case D_PAGE_FOUR:
                            rbUnNormal.setChecked(true);
                            TvTopBar.setText("异常处理");
                            break;
                        case D_PAGE_FIVE:
                            rbOnSite.setChecked(true);
                            TvTopBar.setText("现场检查");
                            break;

                    }
                }

            }
        });

    }
}
