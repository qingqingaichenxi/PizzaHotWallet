package com.xcoinpay.pizza.pizzawallet.globle;

import android.app.Application;

import com.github.sumimakito.quickkv.QuickKV;
import com.github.sumimakito.quickkv.database.KeyValueDatabase;

/**
 * Created by llq on 2017/12/17.
 */

public class MyApplication extends Application {
    public static QuickKV quickKv;
    @Override
    public void onCreate() {
        super.onCreate();
//        ZXingLibrary.initDisplayOpinion(this);


        //数据库的创建
        quickKv = new QuickKV(this);
        KeyValueDatabase pizzaWallet = quickKv.getDatabase("PizzaWallet");

    }
}
