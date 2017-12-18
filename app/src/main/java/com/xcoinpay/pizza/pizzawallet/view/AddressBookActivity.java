package com.xcoinpay.pizza.pizzawallet.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.base.BaseActivity;
import com.xcoinpay.pizza.pizzawallet.presenter.AddressPresenter;

public class AddressBookActivity extends BaseActivity<AddressPresenter> {


    @Override
    public AddressPresenter getPresnter() {
        return new AddressPresenter();
    }

    @Override
    public void init() {
       setSupToolbar("地址薄",R.mipmap.ic_launcher);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dress_book;
    }

    //点击右边的按钮实现的逻辑
    @Override
    protected void onRightClick() {
//        Toast.makeText(this, "添加地址薄", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,AddAddressActivity.class));
    }
}
