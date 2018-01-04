package com.xcoinpay.pizza.pizzawallet.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.adapter.TradeBookAdapter;
import com.xcoinpay.pizza.pizzawallet.base.BaseActivity;
import com.xcoinpay.pizza.pizzawallet.bean.BaseResponse;
import com.xcoinpay.pizza.pizzawallet.bean.Coin;
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
        switch (event.code){
            case Event.Code.SuccessSendDtailCode:
                Coin  coin = (Coin) event.data;
                String id = coin.getHexId();
                BaseResponse.ResponseResult resultData = (BaseResponse.ResponseResult) event.resultData;
                Toast.makeText(this, resultData.getMsg(), Toast.LENGTH_SHORT).show();
                break;
            case Event.Code.FailSendDtailCode:
                Toast.makeText(this, "连接服务失败", Toast.LENGTH_SHORT).show();
                break;

        }
    }


}
