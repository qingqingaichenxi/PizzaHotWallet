package com.xcoinpay.pizza.pizzawallet.view;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.adapter.SearchAdapter;
import com.xcoinpay.pizza.pizzawallet.base.BaseActivity;
import com.xcoinpay.pizza.pizzawallet.bean.Bean;
import com.xcoinpay.pizza.pizzawallet.bean.Event;
import com.xcoinpay.pizza.pizzawallet.presenter.TokenPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class TokenActivity extends BaseActivity<TokenPresenter>  {




    @Override
    public TokenPresenter getPresnter() {
        return new TokenPresenter();
    }

    @Override
    public void init() {
        setSupToolbar("以太Token",R.mipmap.home);


        initData();




    }

    private void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_token;
    }

    //点击Toolbar有右边的按钮
    @Override
    protected void onRightClick() {
        super.onRightClick();
        startActivity(new Intent(this,CommitTokenActivity.class));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Event event){

    }










}
