package com.xcoinpay.pizza.pizzawallet.modle;


import com.xcoinpay.pizza.pizzawallet.bean.BaseResponse;
import com.xcoinpay.pizza.pizzawallet.bean.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by llq on 2017/12/15.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("xcoinpay/user/login")
    Call<BaseResponse<User>> login(@Field("tel") String mPhone, @Field("password") String mPwd);
}
