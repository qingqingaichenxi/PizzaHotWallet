package com.xcoinpay.pizza.pizzawallet.bean;

/**
 * Created by llq on 2017/12/28 0028.
 */

public class Coin {
   private String name;
    private String address;
    private String hexId;

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

    public String getHexId() {
        return hexId;
    }

    public void setHexId(String hexId) {
        this.hexId = hexId;
    }
}
