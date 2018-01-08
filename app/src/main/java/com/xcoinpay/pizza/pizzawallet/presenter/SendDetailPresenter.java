package com.xcoinpay.pizza.pizzawallet.presenter;

import android.util.Log;

import com.xcoinpay.pizza.pizzawallet.base.BasePresenter;
import com.xcoinpay.pizza.pizzawallet.bean.BaseResponse;
import com.xcoinpay.pizza.pizzawallet.bean.HashWapper;
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
    private Call<BaseResponse<HashWapper>> sendCoinCall;

    //发送发出的请求
    public void sendCoin(String hex, String userId, String nonce, String walletAddress ,String coinId){
        sendCoinCall = RetrofitHelper.getInstance().getApiService().sendCoin(hex,userId,nonce,walletAddress,coinId);
        sendCoinCall.enqueue(new Callback<BaseResponse<HashWapper>>() {
            @Override
            public void onResponse(Call<BaseResponse<HashWapper>> call, Response<BaseResponse<HashWapper>> response) {
                BaseResponse<HashWapper> body = response.body();
                HashWapper hashWapper =  body.data;
            BaseResponse.ResponseResult result =  body.getResult();
            if(result.code.equals("200")){
                EventBus.getDefault().post(new Event(Event.Code.SuccessSendDtailCode,hashWapper,result));//返回的数据传递给TradeBookActivity
            }
            else if(result.code.equals("120")){
                EventBus.getDefault().post(new Event(Event.Code.SendNonceCode,hashWapper,result));
            }
            else if(result.code.equals("121")){
                EventBus.getDefault().post(new Event(Event.Code.SendNonceCode121,null,result));
            }
            else {
                Log.i("返回的结果数据",result.code);
                EventBus.getDefault().post(new Event(Event.Code.FailSendDtailCode,null,result));
            }

            }

            @Override
            public void onFailure(Call<BaseResponse<HashWapper>> call, Throwable t) {
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
