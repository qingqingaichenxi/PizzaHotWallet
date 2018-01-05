package com.xcoinpay.pizza.pizzawallet.presenter;

import android.util.Log;

import com.xcoinpay.pizza.pizzawallet.base.BasePresenter;
import com.xcoinpay.pizza.pizzawallet.bean.BaseResponse;
import com.xcoinpay.pizza.pizzawallet.bean.Coin;
import com.xcoinpay.pizza.pizzawallet.bean.Event;
import com.xcoinpay.pizza.pizzawallet.modle.RetrofitHelper;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by llq on 2018/1/3 0003.
 */

public class SendDetailPresenter extends BasePresenter {
    private Call<BaseResponse<Coin>> sendCoinCall;

    //发送发出的请求
    public void sendCoin(String hex, String userId, String nonce, String walletAddress ){
        sendCoinCall = RetrofitHelper.getInstance().getApiService().sendCoin(hex,userId,nonce,walletAddress);
        sendCoinCall.enqueue(new Callback<BaseResponse<Coin>>() {
            @Override
            public void onResponse(Call<BaseResponse<Coin>> call, Response<BaseResponse<Coin>> response) {
                BaseResponse<Coin> body = response.body();
                Coin coin =  body.data;
            BaseResponse.ResponseResult result =  body.getResult();
            if(result.code.equals(200)){
                EventBus.getDefault().post(new Event(Event.Code.SuccessSendDtailCode,coin,result));
            }
            else {
                Log.i("返回的结果数据",result.code);
                EventBus.getDefault().post(new Event(Event.Code.FailSendDtailCode,result,null));
            }

            }

            @Override
            public void onFailure(Call<BaseResponse<Coin>> call, Throwable t) {
                EventBus.getDefault().post(new Event(Event.Code.RequestFailsendDetailRegistCode,null,null));
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
