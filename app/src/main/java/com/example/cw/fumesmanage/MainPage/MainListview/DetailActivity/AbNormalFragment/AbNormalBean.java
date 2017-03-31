package com.example.cw.fumesmanage.MainPage.MainListview.DetailActivity.AbNormalFragment;

/**
 * Created by cw on 2017/3/24.
 */

public class AbNormalBean {
    public int Id;
    public String ItemTitle;
    public double Value;

    public AbNormalBean(int id, String itemTitle, double value) {
        Id = id;
        ItemTitle = itemTitle;
        Value = value;
    }

    public int getId() {
        return Id;
    }
}
