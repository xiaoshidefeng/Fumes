package com.example.cw.fumesmanage.MainPage.MainListview;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cw.fumesmanage.MainPage.MainListview.DetailActivity.DetailActivity;
import com.example.cw.fumesmanage.R;

import java.util.List;

/**
 * Created by cw on 2017/3/23.
 */

public class MainAdapter extends BaseAdapter {

    private List<MainItemBean> mList;

    private ViewHolder viewHolder = null;

    private LayoutInflater mInflater;

//    private ;

    public MainAdapter(Context context, List<MainItemBean> list){
        mList = list;
        mInflater = LayoutInflater.from(context);
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
    public View getView(final int i, View view, ViewGroup viewGroup) {


        viewHolder = null;
        if(view == null){

            viewHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.main_list_item,null);


            viewHolder.name = (TextView)view.findViewById(R.id.id_TVmainListViewItemName);
            viewHolder.value = (TextView)view.findViewById(R.id.id_TVmainListViewItemValue);
//            viewHolder.area = (TextView)view.findViewById(R.id.id_TVmainListViewItemLocation);
            viewHolder.updated_at = (TextView)view.findViewById(R.id.id_TVmainListViewItemUpdateTime);
            viewHolder.rl = (RelativeLayout)view.findViewById(R.id.id_RLmainListView);



            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)view.getTag();
        }


        final MainItemBean bean = mList.get(i);
        viewHolder.name.setText(bean.name);
        viewHolder.value.setText(bean.fval+"mg/m³");
//        viewHolder.area.setText(bean.province+bean.city+bean.area);
        viewHolder.updated_at.setText(bean.updated_at);
        /**
         * 判断数值是否正常 正常显示绿色 不正常显示红色
         * list 里面要传入两个value 一个用于显示 一个用于判断
         */

        if (bean.fval > 2){
            viewHolder.value.setTextColor(Color.RED);
        }else {
            viewHolder.value.setTextColor(Color.rgb(0,153,0));
        }

        viewHolder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                                SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("EnterInfo",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("id",bean.id+"");
                editor.commit();

//                Toast.makeText(view.getContext(),bean.ItemTitle,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                view.getContext().startActivity(intent);


            }
        });

//        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("postInfo",
//                        Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("maintitle",bean.ItemTitle);
//                editor.putString("postone",bean.RawConten);
//                editor.putInt("postid",bean.getId());
//                editor.putString("userheadimg",bean.getUserImgUrl());
//                editor.putString("username",bean.ItemName);
//                editor.putString("creattime",bean.ItemCreatTime);
//                editor.commit();
//
//                Intent intent = new Intent(view.getContext(), PostActivity.class);
//                view.getContext().startActivity(intent);
//            }
//        });

        return view;


    }

    class ViewHolder{
//        public TextView title;
        public TextView value;
//        public TextView id;
//        public TextView enter_long;
        public TextView name;
//        public TextView province;
//        public TextView area;
//        public TextView fx;
//        public TextView fy;
//        public TextView fval;
//        public TextView hood_id;
//        public TextView created_at;
        public TextView updated_at;


        public RelativeLayout rl;


    }
}
