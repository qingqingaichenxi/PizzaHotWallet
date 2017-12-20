package com.xcoinpay.pizza.pizzawallet.bean;

/**
 * Created by llq on 2017/12/20.
 */

public class Event {

    //事件标识吗常量
    public static class Code{
        public static final int LoginSuccess = 1;
        public static final int LoginFail = 2;
        //...
    }



    public int code;//事件标识码
    public Object data;//事件携带的数据对象

    public Event(int code, Object data){
        this.code = code;
        this.data = data;
    }
}
