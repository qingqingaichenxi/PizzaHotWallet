package com.xcoinpay.pizza.pizzawallet.base;

/**
 * Created by llq on 2017/12/15.
 */

public interface IView< P extends  IPresenter> {

    //获取P
    P getPresnter();

    //对控件进行初始化的
    void init();

    //获取控件的布局的
    int getLayoutId();
}
