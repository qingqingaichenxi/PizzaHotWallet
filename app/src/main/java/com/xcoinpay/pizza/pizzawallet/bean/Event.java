package com.xcoinpay.pizza.pizzawallet.bean;

/**
 * Created by llq on 2017/12/20.
 */

public class Event {

    //事件标识吗常量
    public static class Code{
        public static final String RequestSuccess = "1";
        public static final String RequestFail = "2";



        public static final String SuccessCode = "200";
        public static final String FailCode = "500";

        //...
    }



    public String code;//事件标识码
    public Object data;//事件携带的数据对象


    public Event(String code, Object data){
        this.code = code;
        this.data = data;
    }
}
