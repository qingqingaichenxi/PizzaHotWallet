package com.xcoinpay.pizza.pizzawallet.presenter;

import com.xcoinpay.pizza.pizzawallet.base.BasePresenter;
import com.xcoinpay.pizza.pizzawallet.bean.BaseResponse;
import com.xcoinpay.pizza.pizzawallet.bean.Coin;
import com.xcoinpay.pizza.pizzawallet.modle.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by llq on 2017/12/16 0016.
 */

public class HomePresenter extends BasePresenter {

    private Call<BaseResponse<Coin>> sendCoinCall;
    private Call<BaseResponse<Coin>> queryCoinCall;

    //发送发出的请求
    public void sendCoin(String address){
        sendCoinCall = RetrofitHelper.getInstance().getApiService().sendCoin(address);
        sendCoinCall.enqueue(new Callback<BaseResponse<Coin>>() {
            @Override
            public void onResponse(Call<BaseResponse<Coin>> call, Response<BaseResponse<Coin>> response) {

            }

            @Override
            public void onFailure(Call<BaseResponse<Coin>> call, Throwable t) {

            }
        });
    }
    //查询发出的请求
    public void queryCoin(String result) {
        queryCoinCall = RetrofitHelper.getInstance().getApiService().queryCoin(result);
        queryCoinCall.enqueue(new Callback<BaseResponse<Coin>>() {
            @Override
            public void onResponse(Call<BaseResponse<Coin>> call, Response<BaseResponse<Coin>> response) {

            }

            @Override
            public void onFailure(Call<BaseResponse<Coin>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDetory() {
        super.onDetory();
        if(sendCoinCall!=null){
            sendCoinCall.cancel();
        }
        if(queryCoinCall!=null){
            queryCoinCall.cancel();
        }
    }


}
