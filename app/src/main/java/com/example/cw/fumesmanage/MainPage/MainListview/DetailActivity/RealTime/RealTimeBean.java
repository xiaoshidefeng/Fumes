package com.example.cw.fumesmanage.MainPage.MainListview.DetailActivity.RealTime;

/**
 * Created by cw on 2017/3/29.
 */

public class RealTimeBean {
    public int id;
    public int hood_id;
    public double value;
    public String time;
    public String created_at;
    public String updated_at;

    public RealTimeBean(int id, int hood_id, double value
            , String time, String created_at, String updated_at) {
        this.id = id;
        this.hood_id = hood_id;
        this.value = value;
        this.time = time;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
