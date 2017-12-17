

package com.xcoinpay.pizza.pizzawallet.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.base.BaseActivity;
import com.xcoinpay.pizza.pizzawallet.presenter.LoginPresenter;

public class LoginActivity extends BaseActivity<LoginPresenter> {


    @Override
    public LoginPresenter getPresnter() {
        return new LoginPresenter();
    }

    @Override
    public void init() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.login_activity;
    }
}
