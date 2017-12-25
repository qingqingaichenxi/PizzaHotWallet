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
        public static final String SuccessCode = "200";
        public static final String FailCode = "500";



        //http请求完了以后，返回的body中是否是有数据或者是城否成功，有数据就是成功，没有数据就是失败
        //如果一个界面上只有一个请求的话，返回码code，就用以下这两个(SuccessCode,FailCode)，
        //如果一个界面上有两个请求的话，返回码就是一下四个


        //忘记密码页面
        public static final String RequestFailsendforCode = "4";
        public static final String forgetSuccessCode = "202";
        public static final String forgetFailCode = "502";

        public static final String SecondeForSuccessCode = "201";
        public static final String SecondeForFailCode = "501";
        public static final String RequestFails = "3";




/**
 * 注册页面
 *
 * */
        public static final String registSuccessCode = "203";
        public static final String registFailCode = "503";
        public static final String RequestFailsendRegistCode = "5";

        public static final String SecondeRegistSuccessCode = "204";
        public static final String SecondeRegistFailCode = "504";
        public static final String RequestFailRegistCode = "6";


        //...
    }



    public String code;//事件标识码
    public Object data;//事件携带的数据对象(成功的数据对象)
    public  Object resultData;


    public Event(String code, Object data,Object resultData){
        this.code = code;
        this.data = data;
        this.resultData = resultData;
    }
}
