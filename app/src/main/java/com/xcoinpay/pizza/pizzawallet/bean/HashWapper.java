package com.xcoinpay.pizza.pizzawallet.bean;

/**
 * Created by llq on 2017/12/28 0028.
 */

public class HashWapper {
    private String txHash;
    private String nonce;

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }
}
