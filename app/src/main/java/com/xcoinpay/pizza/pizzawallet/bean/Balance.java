package com.xcoinpay.pizza.pizzawallet.bean;

/**
 * Created by llq on 2018/1/4 0004.
 */

public class Balance {
    private String balance;
    private String nonce;
    private String symbol;
    private String walletAddress;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public static class BalanceWrapper{
     private Balance balanceModel;

        public Balance getBalanceModel() {
            return balanceModel;
        }

        public void setBalanceModel(Balance balanceModel) {
            this.balanceModel = balanceModel;
        }
    }
}
