package com.xcoinpay.pizza.pizzawallet.view.fragment;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.base.BaseFragment;
import com.xcoinpay.pizza.pizzawallet.base.IView;
import com.xcoinpay.pizza.pizzawallet.presenter.MyPresenter;

/**
 * Created by llq on 2017/12/16 0016.
 */

public  class MyFragment extends BaseFragment<MyPresenter>{
    @Override
    public MyPresenter getPresnter() {
        return new MyPresenter();
    }

    @Override
    public void init() {
//        toolbar_title.setText("账户");

    }

    @Override
    public int getLayoutId() {
        return R.layout.myfragment;
    }
}
