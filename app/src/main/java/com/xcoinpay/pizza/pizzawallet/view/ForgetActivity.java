package com.xcoinpay.pizza.pizzawallet.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.base.BaseActivity;
import com.xcoinpay.pizza.pizzawallet.presenter.ForgetPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgetActivity extends BaseActivity<ForgetPresenter> {
    @BindView(R.id.tv_getcode)
    TextView getcode;
    @BindView(R.id.forget_commit)
    Button coomit;


    @Override
    public ForgetPresenter getPresnter() {
        return new ForgetPresenter();
    }

    @Override
    public void init() {
        setSupToolbar("忘记密码",0);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String string){

    }

    @OnClick({R.id.tv_getcode,R.id.forget_commit})
    public void click(View view){
        switch (view.getId()){
            case R.id.tv_getcode:
                break;
            case R.id.forget_commit:
                break;
        }
    }
}
