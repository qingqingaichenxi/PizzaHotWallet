package com.xcoinpay.pizza.pizzawallet.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.base.BaseActivity;
import com.xcoinpay.pizza.pizzawallet.presenter.SelectPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SelectBiActivity extends BaseActivity<SelectPresenter> {


    @Override
    public SelectPresenter getPresnter() {
        return new SelectPresenter();
    }

    @Override
    public void init() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_bi;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public  void onEvent(String string){

    }
}
