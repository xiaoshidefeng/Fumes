package com.example.cw.fumesmanage.MainPage.MainListview.DetailActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cw.fumesmanage.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cw on 2017/3/24.
 */

public class OnSiteFragment extends Fragment {

    private ImageView img;
    private Button take_picture;

    private Button btnUpDate;

    private EditText editText;

    public OnSiteFragment(){

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        img=(ImageView)getActivity().findViewById(R.id.id_ImgOnSite);
        take_picture=(Button)getActivity().findViewById(R.id.id_BtnTakePhoto);
        btnUpDate=(Button)getActivity().findViewById(R.id.id_BtnUpPhoto);
        editText = (EditText)getActivity().findViewById(R.id.id_etfeedbackcontent);


        take_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent, Activity.DEFAULT_KEYS_DIALER);
            }
        });

        btnUpDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = editText.getText().toString();
                if(s==null||s.equals("")){
                    Toast.makeText(getContext(),"请输入处理说明",Toast.LENGTH_SHORT).show();

                }else {
                    showDi();
                }

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fg_onsite_layout, container, false);

        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            String sdStatus = Environment.getExternalStorageState();
            if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
                return;
            }
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
            FileOutputStream b = null;
            File file = new File("/sdcard/myImage/");
            file.mkdirs();// 创建文件夹，名称为myimage

            //照片的命名，目标文件夹下，以当前时间数字串为名称，即可确保每张照片名称不相同。
            String str=null;
            Date date=null;
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");//获取当前时间，进一步转化为字符串
            date =new Date();
            str=format.format(date);
            String fileName = "/sdcard/myImage/"+str+".jpg";
            try {
                b = new FileOutputStream(fileName);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    b.flush();
                    b.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (data!= null) {
                    Bitmap cameraBitmap = (Bitmap) data.getExtras().get("data");
                    System.out.println("fdf================="+data.getDataString());
                    img.setImageBitmap(cameraBitmap);

                    System.out.println("成功======"+cameraBitmap.getWidth()+cameraBitmap.getHeight());
                }
            }//finally
        }
    }


    private void showDi(){
        final ProgressDialog dialog = ProgressDialog.show(
                getActivity(), "正在上传...", "请耐心等待...");// 之所以用final定义，主要目的是为了让内部类可以访问方法中定义的参数。
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(3000);

                    //开启ui线程
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(),"上传完成",Toast.LENGTH_SHORT).show();

                        }
                    });

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    dialog.dismiss();

                }

            };
        }.start();
    }
}
