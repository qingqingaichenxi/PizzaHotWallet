package com.xcoinpay.pizza.pizzawallet.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.adapter.TradeBookAdapter;
import com.xcoinpay.pizza.pizzawallet.base.BaseActivity;
import com.xcoinpay.pizza.pizzawallet.bean.Event;
import com.xcoinpay.pizza.pizzawallet.presenter.TradePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TradeBookActivity extends BaseActivity<TradePresenter> {


    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    @BindView(R.id.sf_refresh)
    SwipeRefreshLayout sfRefresh;

    @Override
    public TradePresenter getPresnter() {
        return new TradePresenter();
    }

    @Override
    public void init() {
        setSupToolbar("交易记录", R.mipmap.home1);
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        TradeBookAdapter tradeBookAdapter = new TradeBookAdapter(this);
        recycleview.setAdapter(tradeBookAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_trade_book;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(Event event) {

    }


}
