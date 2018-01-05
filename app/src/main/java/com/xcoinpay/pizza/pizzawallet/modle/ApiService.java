package com.xcoinpay.pizza.pizzawallet.modle;


import com.xcoinpay.pizza.pizzawallet.bean.Balance;
import com.xcoinpay.pizza.pizzawallet.bean.BaseResponse;
import com.xcoinpay.pizza.pizzawallet.bean.Coin;
import com.xcoinpay.pizza.pizzawallet.bean.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by llq on 2017/12/15.
 */

public interface ApiService {

    //登录接口的参数
    @FormUrlEncoded
    @POST("login")
    Call<BaseResponse<User>> login(@Field("tel") String mPhone, @Field("password") String mPwd);



    //注册页面发送短信的接口参数
    @FormUrlEncoded
    @POST("user/sendMessage")
    Call<BaseResponse<User>> sendCode(@Field("tel") String mPhone, @Field("mssageType") int mssageType);

    //注册页面注册的接口参数
    @FormUrlEncoded
    @POST("user/register")
    Call<BaseResponse<User>> regist(@Field("name") String name, @Field("tel") String mPhone,
                                    @Field("password") String pwd, @Field("code") String code);


    //忘记密码接口的参数（发送短信）
    @FormUrlEncoded
    @POST("user/sendMessage")
    Call<BaseResponse<User>> forgetSendeCode(@Field("tel") String mPhone, @Field("mssageType") int messageType);

    //忘记密码接口的参数（提交按钮）
    @FormUrlEncoded
    @POST("user/changeLoginPassword")
    Call<BaseResponse<User>> forgetCommit(@Field("newPassword") String newPassword, @Field("tel") String mPhone, @Field("code") String code);


    //fragmenthome扫描冷钱包发送数字币的信息，发送给后台
    @FormUrlEncoded
    @POST("coinTransfer/saveCoinTransfer")
    Call<BaseResponse<Coin>> sendCoin( @Field("hex") String hex, @Field("userId") String userId,
                                       @Field("nonce") String nonce , @Field("walletAddress") String walletAddress );

    @FormUrlEncoded
    @POST("coin/getBalance")
    Call<BaseResponse<Balance.BalanceWrapper>> queryCoin(@Field("walletAddress") String myAddress,@Field("symbol") String coinId);



}
