package com.xcoinpay.pizza.pizzawallet.presenter;

import android.util.Log;

import com.xcoinpay.pizza.pizzawallet.base.BasePresenter;
import com.xcoinpay.pizza.pizzawallet.base.IPresenter;
import com.xcoinpay.pizza.pizzawallet.bean.BaseResponse;
import com.xcoinpay.pizza.pizzawallet.bean.Event;
import com.xcoinpay.pizza.pizzawallet.bean.User;
import com.xcoinpay.pizza.pizzawallet.modle.RetrofitHelper;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by llq on 2017/12/16.
 */

public class LoginPresenter extends BasePresenter {


     Call<BaseResponse<User>> userCall;

    public void login(String phone, String saltpwd) {

        userCall = RetrofitHelper.getInstance().getApiService().login(phone, saltpwd);
        userCall.enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
                Log.i("返回的结果--------------",response.toString());
                //请求成功，通知UI 更新界面和保存数据

                BaseResponse<User> body = response.body();
                User  user =  body.data;
                BaseResponse<User>.ResponseResult result = body.getResult();
                if(result.code=="200"){
                    EventBus.getDefault().post(new Event(Event.Code.RequestSuccess,user));
                }
                else {
                    Log.i("返回的结果数据",result.code);
                    EventBus.getDefault().post(new Event(Event.Code.FailCode,result));
                }



            }

            @Override
            public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
                String message = t.getMessage();
                String s = t.toString();
                //请求失败,通知用户
                EventBus.getDefault().post(new Event(Event.Code.RequestFail,null));
            }
        });
    }

    @Override
    public void onDetory() {
        super.onDetory();
        if(userCall!=null){
            userCall.cancel();
        }

    }
}
