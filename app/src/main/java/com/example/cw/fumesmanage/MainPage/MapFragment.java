package com.example.cw.fumesmanage.MainPage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.example.cw.fumesmanage.R;

/**
 * Created by cw on 2017/3/22.
 */

public class MapFragment extends android.support.v4.app.Fragment {

    private static MapFragment fragment=null;
    public static final int POSITION=0;

    private MapView mapView;
    private AMap aMap;
    private View mapLayout;

    public static Fragment newInstance(){
        if(fragment==null){
            synchronized(MapFragment.class){
                if(fragment==null){
                    fragment=new MapFragment();
                }
            }
        }
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mapLayout == null) {
            Log.i("sys", "MF onCreateView() null");
            mapLayout = inflater.inflate(R.layout.map_fg_layout, null);
            mapView = (MapView) mapLayout.findViewById(R.id.map);
            mapView.onCreate(savedInstanceState);
            if (aMap == null) {
                aMap = mapView.getMap();
            }
        }else {
            if (mapLayout.getParent() != null) {
                ((ViewGroup) mapLayout.getParent()).removeView(mapLayout);
            }
        }

        for(int i = 18 ;i<27;i++){

            LatLng latLng = new LatLng(i,119.397972);
            Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title("title").snippet("message"));
        }

        LatLng latLng2 = new LatLng(28.006901,120.397972);

        Marker marker2 = aMap.addMarker(new MarkerOptions()
                .position(latLng2).title("title").snippet("message")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.red2d)));


        aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng2));
        return mapLayout;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        Log.i("sys", "mf onResume");
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     * map的生命周期方法
     */
    @Override
    public void onPause() {
        Log.i("sys", "mf onPause");
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     * map的生命周期方法
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.i("sys", "mf onSaveInstanceState");
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     * map的生命周期方法
     */
    @Override
    public void onDestroy() {
        Log.i("sys", "mf onDestroy");
        super.onDestroy();
        mapView.onDestroy();
    }

}
