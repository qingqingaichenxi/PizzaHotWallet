package com.xcoinpay.pizza.pizzawallet.view;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.adapter.WelcomeAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WecomeActivity extends AppCompatActivity {


    @BindView(R.id.wecome_btn_go)
    Button btn_go;
    @BindView(R.id.wecome_viewpager)
    ViewPager viewpager;

    int[] pic = new int[]{R.mipmap.girlb,R.mipmap.girla,R.mipmap.girlc,R.mipmap.girld};
    ArrayList<ImageView> imgs = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wecome);
        ButterKnife.bind(this);

        initImgeView();

        WelcomeAdapter welcomeAdapter = new WelcomeAdapter(imgs);
        viewpager.setAdapter(welcomeAdapter);
        //对viewpager滑动监听
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i=0; i<imgs.size(); i++){
                    if(position == imgs.size()-1){
                        btn_go.setVisibility(View.VISIBLE);
                    }
                    else {
                        btn_go.setVisibility(View.GONE);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initImgeView() {
        for(int i = 0; i<pic.length; i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(pic[i]);
            //再把图片添加在集合中

            imgs.add(imageView);
        }
    }

    @OnClick(R.id.wecome_btn_go)
    public void click(View view){
        startActivity(new Intent(this,MainActivity.class));
    }
}
