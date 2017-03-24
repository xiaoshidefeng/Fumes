package com.example.cw.fumesmanage.MainPage.MainListview.DetailActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

/**
 * Created by cw on 2017/3/24.
 */

public class DetailPagerAdapter extends FragmentPagerAdapter{

    private final int DETAIL_PAGER_COUNT = 5;
    private MsgFragment myFragment1 = null;
    private DayListFragment myFragment2 = null;
    private MonthListFragment myFragment3 = null;
    private AbNormalFragment myFragment4 = null;
    private OnSiteFragment myFragment5 = null;

    public DetailPagerAdapter(FragmentManager fm) {
        super(fm);
        myFragment1 = new MsgFragment();
        myFragment2 = new DayListFragment();
        myFragment3 = new MonthListFragment();
        myFragment4 = new AbNormalFragment();
        myFragment5 = new OnSiteFragment();
    }


    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case DetailActivity.D_PAGE_ONE:
                fragment = myFragment1;
                break;
            case DetailActivity.D_PAGE_TWO:
                fragment = myFragment2;
                break;
            case DetailActivity.D_PAGE_THREE:
                fragment = myFragment3;
                break;
            case DetailActivity.D_PAGE_FOUR:
                fragment = myFragment4;
                break;
            case DetailActivity.D_PAGE_FIVE:
                fragment = myFragment5;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return DETAIL_PAGER_COUNT;
    }


}
