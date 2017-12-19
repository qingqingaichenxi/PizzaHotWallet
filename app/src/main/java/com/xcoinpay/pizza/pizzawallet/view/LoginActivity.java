

package com.xcoinpay.pizza.pizzawallet.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.base.BaseActivity;
import com.xcoinpay.pizza.pizzawallet.presenter.LoginPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> {

    @BindView(R.id.login_tv_regist)
    TextView tv_regist;
    @BindView(R.id.login_tv_forgetpwd)
    TextView tv_forgetpwd;


    @Override
    public LoginPresenter getPresnter() {
        return new LoginPresenter();
    }

    @Override
    public void init() {
        setSupToolbar("登录",0);


    }

    @Override
    public int getLayoutId() {
        return R.layout.login_activity;
    }


//接受传递过来的数据
    @Subscribe(threadMode = ThreadMode.MAIN)
    public  void onEvent(String string){

    }

    @OnClick({R.id.login_tv_regist,R.id.login_tv_forgetpwd})
    public void click(View view){
        switch (view.getId()){
            case R.id.login_tv_regist:
                startActivity(new Intent(this,RegistActivity.class));
                break;
            case R.id.login_tv_forgetpwd:
                startActivity(new Intent(this,ForgetActivity.class));
                break;
        }
    }

}
