package com.xcoinpay.pizza.pizzawallet.bean;

/**
 * Created by llq on 2017/12/22 0022.
 */

public class BaseResponse <T>{

    public T data;
    public ResponseResult result;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseResult getResult() {
        return result;
    }

    public void setResult(ResponseResult result) {
        this.result = result;
    }


    public class ResponseResult {
        public String code;
        public String msg;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
