package com.okhttp.demo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.okhttp.demo.R;

public class GlideActivity extends AppCompatActivity {

    private static final String TAG = "GlideActivity";

    ImageView mImg;
    private String imageUri = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fitbbs%2F1604%2F21%2Fc12%2F20568005_1461203204293_mthumb.jpg&refer=http%3A%2F%2Fimg.pconline.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1634981720&t=03c1e5dbee6ef6bdce99f40eff3cc932";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);

        mImg = findViewById(R.id.ag_center_iv);

        Glide.with(this).load(imageUri).into(mImg);

    }

//    public void getStocks() {
//        for (int i=6;i>=1;i--) {
//            for (int j=1;j<=31;j++) {
//                //非st 非科创 非创业  7月7日创新高 7月7日涨幅大于2 7月7日成交额大于2亿 7月7日成交量小于7月6日成交量的1.5倍 7月7日成交量大于7月6日成交量的0.96倍 (7月7日收盘价-7月7日开盘价)/7月7日开盘价>1.8% 7月7日上影线大于2.5%
//
//                Log.e(TAG, )
//            }
//        }
//    }
}