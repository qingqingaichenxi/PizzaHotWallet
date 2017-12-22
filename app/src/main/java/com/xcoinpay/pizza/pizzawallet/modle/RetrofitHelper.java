package com.xcoinpay.pizza.pizzawallet.modle;

import com.xcoinpay.pizza.pizzawallet.contant.Contant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by llq on 2017/12/15.
 */

public class RetrofitHelper {

    private  ApiService apiService;
    private static RetrofitHelper retrofitHelper = null;
    private  RetrofitHelper(){
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(Contant.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }



    public static RetrofitHelper getInstance(){
        synchronized (RetrofitHelper.class){
            if(retrofitHelper==null){
                retrofitHelper = new RetrofitHelper();
            }
        }

        return retrofitHelper;
    }


    public ApiService getApiService() {
        return apiService;
    }
}
