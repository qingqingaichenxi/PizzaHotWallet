package com.xcoinpay.pizza.pizzawallet.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.xcoinpay.pizza.pizzawallet.R;

/**
 * Created by llq on 2017/12/15.
 */

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IView<P>{

    public P presnter;
    public TextView toolbar_title;
    public ImageView toolbar_right_iv;
    public ImageView toolbar_left_iv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View baseview = LayoutInflater.from(this).inflate(R.layout.base_activity, null);
        FrameLayout contener = baseview.findViewById(R.id.baseview_fl_contaner);

        View childView = LayoutInflater.from(this).inflate(getLayoutId(), null);
        contener.addView(childView);

        presnter = getPresnter();

        init();

        initToolbar();



    }

    protected  void initToolbar(){
        toolbar_left_iv = findViewById(R.id.toolbar_left_iv);
        toolbar_right_iv = findViewById(R.id.toolbar_right_iv);
        toolbar_title = findViewById(R.id.toolbar_tv);
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presnter.onDetory();
    }
}
