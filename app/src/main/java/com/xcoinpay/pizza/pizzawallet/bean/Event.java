package com.xcoinpay.pizza.pizzawallet.bean;

/**
 * Created by llq on 2017/12/20.
 */

public class Event {

    //事件标识吗常量
    public static class Code{
        //http请求是否是成功,如果一个界面有两个网络请求的话，就会有两个返回的失败的code.所以定义了。RequestFail   RequestFails
        public static final String RequestSuccess = "1";
        public static final String RequestFail = "2";
        public static final String RequestFails = "3";


        //http请求完了以后，返回的body中是否是有数据或者是城否成功，有数据就是成功，没有数据就是失败
        //如果一个界面上只有一个请求的话，返回码code，就用以下这两个(SuccessCode,FailCode)，
        //如果一个界面上有两个请求的话，返回码就是一下四个
        public static final String SuccessCode = "200";
        public static final String FailCode = "500";


        public static final String SecondeSuccessCode = "201";
        public static final String SecondeFailCode = "501";

        //...
    }



    public String code;//事件标识码
    public Object data;//事件携带的数据对象


    public Event(String code, Object data){
        this.code = code;
        this.data = data;
    }
}
