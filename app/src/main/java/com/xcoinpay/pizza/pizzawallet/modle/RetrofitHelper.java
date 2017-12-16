package com.xcoinpay.pizza.pizzawallet.modle;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by llq on 2017/12/15.
 */

public class RetrofitHelper {

    public static final  RetrofitHelper retrofitHelper = new RetrofitHelper();

    private void RetrofitHelper(){

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("xxxxxxx")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofit.create(ApiService.class);
    }

    public RetrofitHelper getInstance(){
        return retrofitHelper;
    }
}
