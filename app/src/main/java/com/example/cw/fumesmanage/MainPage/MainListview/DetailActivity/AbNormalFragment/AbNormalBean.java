package com.example.cw.fumesmanage.MainPage.MainListview.DetailActivity.AbNormalFragment;

/**
 * Created by cw on 2017/3/24.
 */

public class AbNormalBean {
    public int Id;
    public String ItemTitle;
    public String ItemValue;

    public AbNormalBean(int id, String itemTitle, String itemValue) {
        Id = id;
        ItemTitle = itemTitle;
        ItemValue = itemValue;
    }

    public int getId() {
        return Id;
    }
}
