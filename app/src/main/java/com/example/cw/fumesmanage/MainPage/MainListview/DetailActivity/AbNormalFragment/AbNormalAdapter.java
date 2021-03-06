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
                        .setMessage("如何处理" + "\n油烟值：" + bean.Value + "mg/m³")
                        .setNeutralButton("关闭", null)
                        .setPositiveButton("电话通知", new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(view.getContext(), "电话通知处理", Toast.LENGTH_SHORT).show();
                            }

                        })
                        .setNegativeButton("现场处理", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(view.getContext(), "准备前往现场处理", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();



            }
        });

        return view;

    }

    class ViewHolder{
        public TextView title;
        public TextView value;

        public LinearLayout LL;


    }
}
