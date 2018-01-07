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


     Call<BaseResponse<User.UserWapper>> userCall;

    public void login(String phone, String saltpwd) {

        userCall = RetrofitHelper.getInstance().getApiService().login(phone, saltpwd);
        userCall.enqueue(new Callback<BaseResponse<User.UserWapper>>() {
            @Override
            public void onResponse(Call<BaseResponse<User.UserWapper>> call, Response<BaseResponse<User.UserWapper>> response) {
                Log.i("返回的结果--------------",response.toString());
                //请求成功，通知UI 更新界面和保存数据

                BaseResponse<User.UserWapper> body = response.body();
                User  user =  body.data.getUser();
                BaseResponse<User.UserWapper>.ResponseResult result = body.getResult();

                if(result.code.equals("200")){

                    EventBus.getDefault().post(new Event(Event.Code.SuccessCode,user,result));
                }
                else {
                    Log.i("返回的结果数据",result.code);
                    EventBus.getDefault().post(new Event(Event.Code.FailCode,result,null));
                }



            }

            @Override
            public void onFailure(Call<BaseResponse<User.UserWapper>> call, Throwable t) {
                String message = t.getMessage();
                String s = t.toString();
                //请求失败,通知用户
                EventBus.getDefault().post(new Event(Event.Code.RequestFail,null,null));
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
