package com.example.cw.fumesmanage.Tools;

import com.amap.api.maps2d.model.LatLng;

/**
 * Created by cw on 2017/3/29.
 */

public class MapBean {
    public LatLng latLng;
    public double value;
    public int status;
    public int id;

    public MapBean(LatLng latLng, double value, int status, int id) {
        this.latLng = latLng;
        this.value = value;
        this.status = status;
        this.id = id;
    }



}
