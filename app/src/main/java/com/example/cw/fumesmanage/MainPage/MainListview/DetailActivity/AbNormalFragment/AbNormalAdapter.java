package com.example.cw.fumesmanage.MainPage.MainListview.DetailActivity.AbNormalFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cw.fumesmanage.R;

import java.util.List;

/**
 * Created by cw on 2017/3/24.
 */

public class AbNormalAdapter extends BaseAdapter{

    private List<AbNormalBean> mList;

    private ViewHolder viewHolder = null;

    private LayoutInflater mInflater;


    public AbNormalAdapter(Context context, List<AbNormalBean> list){
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
            view = mInflater.inflate(R.layout.detail_fg_abnormal_list_item_layout,null);


            viewHolder.title = (TextView)view.findViewById(R.id.id_TVListItemAbnormal);
            viewHolder.value = (TextView)view.findViewById(R.id.id_TVAbnormalValue);
            viewHolder.LL = (LinearLayout)view.findViewById(R.id.id_AbnormalLL);

            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)view.getTag();
        }


        final AbNormalBean bean = mList.get(i);
        viewHolder.title.setText(bean.ItemTitle);
        viewHolder.value.setText(bean.Value + "mg/m³");
        viewHolder.value.setTextColor(Color.RED);

        viewHolder.LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new AlertDialog.Builder(view.getContext())
                        //.setIcon(R.drawable.icon)
                        .setTitle("处理")
                        .setMessage("是否处理" + "\n油烟值：" + bean.Value + "mg/m³")
                        .setNegativeButton("不处理",null)
                        .setPositiveButton("处理", new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                //Log.v(TAG,"你点击了确定");
                                //activity finish
                                //finish();
                                Toast.makeText(view.getContext(), "处理成功", Toast.LENGTH_SHORT).show();
                            }

                        })
                        .show();


//                Toast.makeText(view.getContext(),bean.ItemTitle,Toast.LENGTH_SHORT).show();

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
        public TextView title;
        public TextView value;

        public LinearLayout LL;


    }
}
