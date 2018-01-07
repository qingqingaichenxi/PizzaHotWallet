package com.xcoinpay.pizza.pizzawallet.presenter;

import android.widget.Toast;

import com.xcoinpay.pizza.pizzawallet.base.BasePresenter;
import com.xcoinpay.pizza.pizzawallet.bean.BaseResponse;
import com.xcoinpay.pizza.pizzawallet.bean.Event;
import com.xcoinpay.pizza.pizzawallet.bean.User;
import com.xcoinpay.pizza.pizzawallet.modle.RetrofitHelper;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by llq on 2017/12/19 0019.
 */

public class RegistPresenter extends BasePresenter {

    private Call<BaseResponse<Object>> sendCall;
    private Call<BaseResponse<Object>> registCall;


    //注册
    public void regist( String name, String mobileNO, String pwdNo,String code) {
        registCall = RetrofitHelper.getInstance().getApiService().regist(name, mobileNO, pwdNo, code);
        registCall.enqueue(new Callback<BaseResponse<Object>>() {
            @Override
            public void onResponse(Call<BaseResponse<Object>> call, Response<BaseResponse<Object>> response) {
                BaseResponse<Object> body = response.body();
                Object user =  body.data;
               BaseResponse.ResponseResult  result = body.getResult();
               if(result.code.equals("200")){
                   EventBus.getDefault().post(new Event(Event.Code.registSuccessCode,user,result));
               }
               else {
                   EventBus.getDefault().post(new Event(Event.Code.registFailCode,result,null));
               }
            }

            @Override
            public void onFailure(Call<BaseResponse<Object>> call, Throwable t) {
                EventBus.getDefault().post(new Event(Event.Code.RequestFailRegistCode,null,null));
            }
        });
    }


//发送验证码
    public void sendCode(String phone, int i) {
        sendCall = RetrofitHelper.getInstance().getApiService().sendCode(phone, i);
        sendCall.enqueue(new Callback<BaseResponse<Object>>() {
            @Override
            public void onResponse(Call<BaseResponse<Object>> call, Response<BaseResponse<Object>> response) {
                BaseResponse<Object> body = response.body();
                Object user = body.data;
                BaseResponse<Object>.ResponseResult result = body.getResult();
                if(result.code.equals("200")){
                    EventBus.getDefault().post(new Event(Event.Code.SecondeRegistSuccessCode,user,result));
                }
                else {
                    EventBus.getDefault().post(new Event(Event.Code.SecondeRegistFailCode,result,null));
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Object>> call, Throwable t) {
                    EventBus.getDefault().post(new Event(Event.Code.RequestFailsendRegistCode,null,null));
            }
        });
    }

    @Override
    public void onDetory() {
        super.onDetory();
        if(sendCall!=null){
            sendCall.cancel();
        }
        if (registCall!=null){
            registCall.cancel();
        }
    }
}

