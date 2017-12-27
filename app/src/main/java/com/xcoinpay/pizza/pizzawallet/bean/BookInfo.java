package com.xcoinpay.pizza.pizzawallet.bean;

import java.io.Serializable;

/**
 * Created by llq on 2017/12/27 0027.
 */

public class BookInfo implements Serializable{
    private String coin;
    private String name;
    private String address;

    public BookInfo(String coin, String name, String address) {
        this.coin = coin;
        this.name = name;
        this.address = address;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
