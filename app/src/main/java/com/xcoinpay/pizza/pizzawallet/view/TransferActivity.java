package com.xcoinpay.pizza.pizzawallet.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.base.BaseActivity;
import com.xcoinpay.pizza.pizzawallet.base.NothingPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransferActivity extends BaseActivity<NothingPresenter> {
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.btn_ok)
    Button btn_ok;


    @Override
    public NothingPresenter getPresnter() {
        return new NothingPresenter();
    }

    @Override
    public void init() {

        setSupToolbar("传送信息"+"BTC", 0);
        initData();

    }

    private void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_transfer;
    }

    @OnClick(R.id.btn_ok)
    public void click(View view){
        finish();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(){

    }
}
