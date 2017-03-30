package com.example.cw.fumesmanage.MainPage.MainListview.DetailActivity.Monthly;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cw.fumesmanage.R;

import java.util.List;

/**
 * Created by cw on 2017/3/30.
 */

public class MonthAdapter extends BaseAdapter {


    private List<MonthListBean> mList;

    private ViewHolder viewHolder = null;

    private LayoutInflater mInflater;

    public MonthAdapter(Context context, List<MonthListBean> mList) {
        this.mList = mList;
        this.mInflater = mInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        viewHolder = null;
        if(view == null){

            viewHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.detail_fg_month_listview_item_layout,null);


            viewHolder.time = (TextView)view.findViewById(R.id.id_TVListItemMonthDay);
            viewHolder.maxn = (TextView)view.findViewById(R.id.id_TVMaxValue);
            viewHolder.minn = (TextView)view.findViewById(R.id.id_TVMinValue);
            //viewHolder.rl = (RelativeLayout)view.findViewById(R.id.id_RLmainListView);

            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)view.getTag();
        }


        final MonthListBean bean = mList.get(i);
        viewHolder.time.setText(bean.time);
        viewHolder.maxn.setText(bean.maxn+"mg/m³");
        viewHolder.minn.setText(bean.minn+"mg/m³");

        if (bean.maxn > 1.8){
            viewHolder.maxn.setTextColor(Color.RED);
        }else {

            viewHolder.maxn.setTextColor(Color.rgb(0,153,0));
        }

        if (bean.minn > 1.8){
            viewHolder.minn.setTextColor(Color.RED);
        }else {

            viewHolder.minn.setTextColor(Color.rgb(0,153,0));
        }

        return view;
    }


    class ViewHolder{
        public TextView time;
        public TextView maxn;
        public TextView minn;

        //public RelativeLayout rl;


    }
}
