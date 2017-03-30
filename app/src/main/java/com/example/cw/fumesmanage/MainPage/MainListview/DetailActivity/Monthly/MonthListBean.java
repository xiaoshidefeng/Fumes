package com.example.cw.fumesmanage.MainPage.MainListview.DetailActivity.Monthly;

/**
 * Created by cw on 2017/3/30.
 */

public class MonthListBean {
    public int id;
    public String time;
    public String created_at;
    public String updated_at;
    public double maxn;
    public double minn;

    public MonthListBean(int id, String time, String created_at, String updated_at, double maxn, double minn) {
        this.id = id;
        this.time = time;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.maxn = maxn;
        this.minn = minn;
    }
}
