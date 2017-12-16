package com.xcoinpay.pizza.pizzawallet.view.fragment;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.base.BaseFragment;
import com.xcoinpay.pizza.pizzawallet.presenter.HomePresenter;

/**
 * Created by llq on 2017/12/16 0016.
 */

public class HomeFragment extends BaseFragment<HomePresenter> {

    @Override
    public HomePresenter getPresnter() {
        return new HomePresenter();
    }

    @Override
    public void init() {
//        toolbar_title.setText("首页");
    }

    @Override
    public int getLayoutId() {
        return R.layout.homefragment;
    }
}
