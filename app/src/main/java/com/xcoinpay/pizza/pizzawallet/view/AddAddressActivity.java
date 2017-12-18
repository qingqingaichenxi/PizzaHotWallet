package com.xcoinpay.pizza.pizzawallet.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.base.BaseActivity;
import com.xcoinpay.pizza.pizzawallet.presenter.AddAddressPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class AddAddressActivity extends BaseActivity<AddAddressPresenter> {
    @BindView(R.id.ll_selectbi)
    LinearLayout ll_selectebi;
    @BindView(R.id.selecte_tv_bi)
    TextView selete_bi;
    @BindView(R.id.add_iv_bi)
    ImageView add_iv_bi;


    @Override
    public AddAddressPresenter getPresnter() {
        return new AddAddressPresenter();
    }

    @Override
    public void init() {
        setSupToolbar("添加地址",0);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_address;
    }
   @OnClick()
    public void click(View view ){
        switch (view.getId()){
            case R.id.ll_selectbi:
                startActivity(new Intent(this,SelectBiActivity.class));
                break;
            case R.id.add_iv_bi:
                break;
        }
   }
}
