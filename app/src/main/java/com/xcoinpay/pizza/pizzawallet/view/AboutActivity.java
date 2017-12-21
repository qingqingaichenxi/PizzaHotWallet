package com.xcoinpay.pizza.pizzawallet.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.base.BaseActivity;
import com.xcoinpay.pizza.pizzawallet.base.NothingPresenter;
import com.xcoinpay.pizza.pizzawallet.bean.Event;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class AboutActivity extends BaseActivity<NothingPresenter> {


    @Override
    public NothingPresenter getPresnter() {
        return new NothingPresenter();
    }

    @Override
    public void init() {
        setSupToolbar("关于",0);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Event event){

    }
}
