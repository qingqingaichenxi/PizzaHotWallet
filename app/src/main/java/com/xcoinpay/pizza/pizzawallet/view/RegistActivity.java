package com.xcoinpay.pizza.pizzawallet.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.base.BaseActivity;
import com.xcoinpay.pizza.pizzawallet.presenter.RegistPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class RegistActivity extends BaseActivity<RegistPresenter> {


    @Override
    public RegistPresenter getPresnter() {
        return new RegistPresenter();
    }

    @Override
    public void init() {
        setSupToolbar("注册",0);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_regist;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String string){

    }
}
