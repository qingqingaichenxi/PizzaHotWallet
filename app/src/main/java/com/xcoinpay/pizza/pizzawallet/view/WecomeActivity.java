package com.xcoinpay.pizza.pizzawallet.view;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.adapter.WelcomeAdapter;
import com.xcoinpay.pizza.pizzawallet.util.DensityUtils;
import com.xcoinpay.pizza.pizzawallet.util.SPUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WecomeActivity extends AppCompatActivity {


    @BindView(R.id.wecome_btn_go)
    Button btn_go;
    @BindView(R.id.wecome_viewpager)
    ViewPager viewpager;

    @BindView(R.id.ll_guide_point)
    LinearLayout linearLayoutGuide;
    @BindView(R.id.guide_point)
    View viewPoint;

    String IS_FIRST_GOIN = "is_first_goin";

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
                int pointDistance = DensityUtils.dip2px(getApplicationContext(),20);
                //计算红点的左边距
                float leftMargrain=pointDistance*(position+positionOffset);
                //设置红点的左边距
                RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) viewPoint.getLayoutParams();
                //对folat类型进行四舍五入，
                params.leftMargin=Math.round(leftMargrain);
                //设置位置
                viewPoint.setLayoutParams(params);


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


            //获取点
            View pointView=new View(getApplicationContext());
            pointView.setBackgroundResource(R.drawable.shap_gray_piont);
            //设置灰色点的显示大小
            float dip=10;
            float distance=10;
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(
                    DensityUtils.dip2px(getApplicationContext(),dip),
                    DensityUtils.dip2px(getApplicationContext(),dip));//单位是px,不是dp,为了做适配需要装换
            //设置点与点的距离,第一个点除外
            if (i!=0) {
                params.leftMargin = DensityUtils.dip2px(getApplicationContext(),distance);//px
            }
            pointView.setLayoutParams(params);


            linearLayoutGuide.addView(pointView);

        }
    }

    @OnClick(R.id.wecome_btn_go)
    public void click(View view){


//        SPUtils.putBoolean(this,IS_FIRST_GOIN,false);
        boolean is_first = SPUtils.getBoolean(this, IS_FIRST_GOIN, true);
        if(is_first){
            SPUtils.putBoolean(this,IS_FIRST_GOIN,false);
            finish();

        }
        else {
            startActivity(new Intent(this,MainActivity.class));
            finish();

        }
    }
}
