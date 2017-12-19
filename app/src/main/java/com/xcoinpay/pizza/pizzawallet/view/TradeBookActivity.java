package com.xcoinpay.pizza.pizzawallet.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.base.BaseActivity;
import com.xcoinpay.pizza.pizzawallet.presenter.TradePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class TradeBookActivity extends BaseActivity<TradePresenter> {


    @Override
    public TradePresenter getPresnter() {
        return new TradePresenter();
    }

    @Override
    public void init() {
        setSupToolbar("交易记录",R.mipmap.home1);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_trade_book;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(String string){

    }
}
