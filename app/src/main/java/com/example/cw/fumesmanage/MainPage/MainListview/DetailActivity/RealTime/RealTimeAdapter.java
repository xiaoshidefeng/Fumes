package com.example.cw.fumesmanage.MainPage.MainListview.DetailActivity.RealTime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cw.fumesmanage.R;

import java.util.List;

/**
 * Created by cw on 2017/3/29.
 */

public class RealTimeAdapter extends BaseAdapter {


    private List<RealTimeBean> mList;

    private ViewHolder viewHolder = null;

    private LayoutInflater mInflater;

    public RealTimeAdapter(Context context, List<RealTimeBean> mList) {
        this.mInflater = mInflater.from(context);
        this.mList = mList;
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
            view = mInflater.inflate(R.layout.detail_fg_daylist_item_layout,null);


            viewHolder.time = (TextView)view.findViewById(R.id.id_TVListItemRealSeeTime);
            viewHolder.value = (TextView)view.findViewById(R.id.id_TVRealItemValue);
            //viewHolder.rl = (RelativeLayout)view.findViewById(R.id.id_RLmainListView);

            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)view.getTag();
        }


        final RealTimeBean bean = mList.get(i);
        viewHolder.time.setText(bean.time);
        viewHolder.value.setText(bean.value+"mg/m³");


        return view;
    }


    class ViewHolder{
        public TextView time;
        public TextView value;

        //public RelativeLayout rl;


    }
}
