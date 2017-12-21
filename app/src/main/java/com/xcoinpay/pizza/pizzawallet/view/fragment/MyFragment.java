package com.xcoinpay.pizza.pizzawallet.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.base.BaseFragment;
import com.xcoinpay.pizza.pizzawallet.base.IView;
import com.xcoinpay.pizza.pizzawallet.presenter.MyPresenter;
import com.xcoinpay.pizza.pizzawallet.view.AboutActivity;
import com.xcoinpay.pizza.pizzawallet.view.LoginActivity;
import com.xcoinpay.pizza.pizzawallet.view.TokenActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by llq on 2017/12/16 0016.
 */

public  class MyFragment extends BaseFragment<MyPresenter>{
    @BindView(R.id.ll_login)
    LinearLayout ll_login;

    @BindView(R.id.ll_token)
    LinearLayout ll_token;
    @BindView(R.id.ll_update)
    LinearLayout ll_update;
    @BindView(R.id.ll_about)
    LinearLayout ll_about;
    @Override
    public MyPresenter getPresnter() {
        return new MyPresenter();
    }

    @Override
    public void init() {
       toolbar.setVisibility(View.GONE);

    }

    @Override
    public int getLayoutId() {
        return R.layout.myfragment;
    }
    @OnClick({R.id.ll_login,R.id.ll_token,R.id.ll_update,R.id.ll_about})
    public void click(View view){
        switch (view.getId()){
            case R.id.ll_login:
                startActivity(new Intent(getActivity(),LoginActivity.class));
                break;
            case R.id.ll_token:
                startActivity(new Intent(getActivity(),TokenActivity.class));
                break;
            case R.id.ll_update:
                break;
            case R.id.ll_about:
                startActivity(new Intent(getActivity(),AboutActivity.class));
                break;
    }
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public  void onEvent(String string){

    }
}
