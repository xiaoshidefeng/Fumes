package com.example.cw.fumesmanage.MainPage.MainListview;

/**
 * Created by cw on 2017/3/23.
 */

public class MainItemBean {
    public int id;
    public String enter_long;
    public String name;
    public String province;
    public String city;
    public String area;
    public float fx;
    public float fy;
    public float fval;
    public int hood_id;
    public String created_at;
    public String updated_at;

    public MainItemBean(int id, String enter_long, String name,
                        String province, String city, String area, float fx,
                        float fy, float fval, int hood_id,
                        String created_at, String updated_at) {
        this.id = id;
        this.enter_long = enter_long;
        this.name = name;
        this.province = province;
        this.city = city;
        this.area = area;
        this.fx = fx;
        this.fy = fy;
        this.fval = fval;
        this.hood_id = hood_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }
}
