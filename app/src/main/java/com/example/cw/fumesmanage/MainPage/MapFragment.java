package com.example.cw.fumesmanage.MainPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.Marker;
import com.example.cw.fumesmanage.MainPage.MainListview.DetailActivity.DetailActivity;
import com.example.cw.fumesmanage.R;
import com.example.cw.fumesmanage.Tools.MapBean;
import com.example.cw.fumesmanage.Tools.NetWorkMap;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cw on 2017/3/22.
 */

public class MapFragment extends android.support.v4.app.Fragment {

    private static MapFragment fragment=null;
    public static final int POSITION=0;
    String getAll = "http://120.25.90.170/enterprises";
    private URL url;
    private List<MapBean> listMapBean = new ArrayList<>();


    private MapView mapView;
    private AMap aMap;
    private View mapLayout;
    public AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;

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


        try {
            url = new URL(getAll);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        NetWorkMap netWorkMap = new NetWorkMap(url,listMapBean,aMap,getContext());

        netWorkMap.enterprises();


//
//        float f = 28.463f;
//        for(int i = 18 ;i<27;i++){
//
//
//            LatLng latLng = new LatLng(f+=0.1,119.9);
//            Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title("title").snippet("message"));
//        }
//
//        LatLng latLng2 = new LatLng(28.463,119.909);
//
//        Marker marker2 = aMap.addMarker(new MarkerOptions()
//                .position(latLng2).title("titless").snippet("message\nmsg")
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.red2d)));
//
        // 定义 Marker 点击事件监听
        AMap.OnMarkerClickListener markerClickListener = new AMap.OnMarkerClickListener() {
            // marker 对象被点击时回调的接口
            // 返回 true 则表示接口已响应事件，否则返回false
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                startActivity(intent);

                //Toast.makeText(getActivity(),marker.getTitle().toString(),Toast.LENGTH_SHORT).show();
                return true;
            }
        };

        // 绑定 Marker 被点击事件
        aMap.setOnMarkerClickListener(markerClickListener);
//
//        aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng2));

//
        mLocationClient = new AMapLocationClient(getActivity());
//初始化定位参数
        mLocationOption = new AMapLocationClientOption();
//设置定位监听
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        //定位成功回调信息，设置相关消息
                        aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                        aMapLocation.getLatitude();//获取纬度
                        aMapLocation.getLongitude();//获取经度
                        aMapLocation.getAccuracy();//获取精度信息
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(aMapLocation.getTime());
                        df.format(date);//定位时间
                    } else {
                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                    }
                }
            }
        });
//设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(200000);
//设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
// 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
// 在定位结束后，在合适的生命周期调用onDestroy()方法
// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
//启动定位
        mLocationClient.startLocation();

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
