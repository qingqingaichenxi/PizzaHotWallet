package com.xcoinpay.pizza.pizzawallet.presenter;

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

public class ForgetPresenter extends BasePresenter {

    private Call<BaseResponse<User>> sendCodeCall;
    private Call<BaseResponse<User>> commitCodeCall;

    //发送验证码的方法
    public void sendCode(String phone, int i) {
        sendCodeCall = RetrofitHelper.getInstance().getApiService().forgetSendeCode(phone, i);
        sendCodeCall.enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
                BaseResponse<User> body = response.body();
               User user =  body.data;
                BaseResponse<User>.ResponseResult result = body.getResult();
                if(result.code.equals("200")){
                    //成功以后传递的数据
                    EventBus.getDefault().post(new Event(Event.Code.SecondeForSuccessCode,user,result));
                }
                else {
                    //传递失败的消息
                    EventBus.getDefault().post(new Event(Event.Code.SecondeForFailCode,result,result));
                }



            }

            @Override
            public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
                EventBus.getDefault().post(new Event(Event.Code.RequestFails,null,null));

            }
        });
    }

    //提忘记密码的方法
    public void commitCode(String newPwd, String phone, String code) {
        commitCodeCall = RetrofitHelper.getInstance().getApiService().forgetCommit(newPwd, phone, code);
        commitCodeCall.enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
                BaseResponse<User> body = response.body();
               User user =  body.getData();//封装的是成功的信息和数据
               BaseResponse.ResponseResult result = body.getResult();//封装的是失败的信息
                if(result.code.equals("200")){
                    //发送成功以后传递的数据
                    EventBus.getDefault().post(new Event(Event.Code.forgetSuccessCode,user,result));
                }
               else {
                    //失败传递的数据
                    EventBus.getDefault().post(new Event(Event.Code.forgetFailCode,result,null));
                }


            }

            @Override
            public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
                EventBus.getDefault().post(new Event(Event.Code.RequestFailsendforCode,null,null));
            }
        });

    }
}
