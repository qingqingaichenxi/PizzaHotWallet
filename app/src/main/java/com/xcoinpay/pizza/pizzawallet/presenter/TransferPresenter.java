package com.xcoinpay.pizza.pizzawallet.presenter;

import android.util.Log;

import com.xcoinpay.pizza.pizzawallet.base.BasePresenter;
import com.xcoinpay.pizza.pizzawallet.bean.Balance;
import com.xcoinpay.pizza.pizzawallet.bean.BaseResponse;
import com.xcoinpay.pizza.pizzawallet.bean.Coin;
import com.xcoinpay.pizza.pizzawallet.bean.Event;
import com.xcoinpay.pizza.pizzawallet.modle.RetrofitHelper;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by llq on 2018/1/4 0004.
 */

public class TransferPresenter extends BasePresenter {
    private Call<BaseResponse<Balance.BalanceWrapper>> queryCoinCall;


    //查询发出的请求
    public void queryCoin(String address,String id) {
        queryCoinCall = RetrofitHelper.getInstance().getApiService().queryCoin(address,id);
        queryCoinCall.enqueue(new Callback<BaseResponse<Balance.BalanceWrapper>>() {
            @Override
            public void onResponse(Call<BaseResponse<Balance.BalanceWrapper>> call, Response<BaseResponse<Balance.BalanceWrapper>> response) {
//                Log.i("reponse",response.message());
                BaseResponse<Balance.BalanceWrapper> body = response.body();
               Balance.BalanceWrapper balanceWrapper =  body.data;
                BaseResponse.ResponseResult result = body.getResult();
               if(result.code.equals("200")){
                   EventBus.getDefault().post(new Event(Event.Code.QuerySuccessCode,balanceWrapper.getBalanceModel(),result));
               }
               else {
                   EventBus.getDefault().post(new Event(Event.Code.QueryFailCode,result,null));
               }

            }

            @Override
            public void onFailure(Call<BaseResponse<Balance.BalanceWrapper>> call, Throwable t) {
                t.printStackTrace();
                EventBus.getDefault().post(new Event(Event.Code.RequestQueryFailCode,null,null));
            }
        });
    }

    @Override
    public void onDetory() {
        super.onDetory();

        if(queryCoinCall!=null){
            queryCoinCall.cancel();
        }
    }
}
