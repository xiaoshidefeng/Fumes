package com.example.cw.fumesmanage.MainPage.MainListview;

/**
 * Created by cw on 2017/3/23.
 */

public class MainItemBean {
    public int Id;
    public String ItemTitle;
    public String ItemValue;

    public MainItemBean(int id, String itemTitle, String itemValue) {
        Id = id;
        ItemTitle = itemTitle;
        ItemValue = itemValue;
    }

    public int getId() {
        return Id;
    }
}
