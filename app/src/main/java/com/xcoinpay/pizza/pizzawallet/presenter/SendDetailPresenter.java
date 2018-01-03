package com.xcoinpay.pizza.pizzawallet.presenter;

import com.xcoinpay.pizza.pizzawallet.base.BasePresenter;
import com.xcoinpay.pizza.pizzawallet.bean.BaseResponse;
import com.xcoinpay.pizza.pizzawallet.bean.Coin;
import com.xcoinpay.pizza.pizzawallet.modle.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by llq on 2018/1/3 0003.
 */

public class SendDetailPresenter extends BasePresenter {
    private Call<BaseResponse<Coin>> sendCoinCall;

    //发送发出的请求
    public void sendCoin(String address, String userid){
        sendCoinCall = RetrofitHelper.getInstance().getApiService().sendCoin(address,userid);
        sendCoinCall.enqueue(new Callback<BaseResponse<Coin>>() {
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
    }
}
