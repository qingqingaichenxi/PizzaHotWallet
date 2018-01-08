package com.xcoinpay.pizza.pizzawallet.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.base.BaseActivity;
import com.xcoinpay.pizza.pizzawallet.bean.BaseResponse;
import com.xcoinpay.pizza.pizzawallet.bean.Event;
import com.xcoinpay.pizza.pizzawallet.bean.HashWapper;
import com.xcoinpay.pizza.pizzawallet.contant.Contant;
import com.xcoinpay.pizza.pizzawallet.presenter.SendDetailPresenter;
import com.xcoinpay.pizza.pizzawallet.util.SPUtils;
import com.xcoinpay.pizza.pizzawallet.widget.ProgressDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendDetailActivity extends BaseActivity<SendDetailPresenter> {


    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.tv_coinid)
    TextView tvCoinid;
    @BindView(R.id.tv_coin_address)
    TextView tvCoinAddress;
    @BindView(R.id.btn_send)
    Button btnSend;

//    private static final String USER_ID = null;
    private String result;
    private String hex;
    private String walletAddress;
    private String nonce= "0";
    private String addresspay;
    private String count;
    private String coinId;
    private ProgressDialog progressDialog;

    @Override
    public SendDetailPresenter getPresnter() {
        return new SendDetailPresenter();
    }

    @Override
    public void init() {
        setSupToolbar("发送交易详情", 0);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_send_detail;
    }

    //扫描发送数据传过来的信息,是从冷端传过来的发送数据
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onEvent(Event event ) {
        switch (event.code){
            case Event.Code.SendCode:
                result = (String) event.data;
                Log.i("+++++++++",result);
                byte[] decode = Base64.decode(result, 0);
                String s = new String(decode);
                Log.i("++++++++",s);
               String[] arr =  s.split(",");
                hex = arr[0];
                walletAddress = arr[1];
                nonce = arr[2];
                addresspay = arr[3];
                count = arr[4];
                coinId = arr[5];
                Log.i("result send  ",s);
                Toast.makeText(this, "扫描成功" + result, Toast.LENGTH_SHORT).show();
                splashUi(addresspay,count,coinId);

                break;
//            case :
//                break;
//            case :
//                break;

        }


    }

    private void splashUi(String addresspay, String count, String coinId) {
        tvCount.setText(count);
        tvCoinid.setText(coinId);
        tvCoinAddress.setText(addresspay);

    }

    private void sendCoin(String nonce) {
        String userId = SPUtils.getString(this, Contant.USER_ID);
        Log.i("pppppppppp","hex:"+hex);
        Log.i("pppppppppp","userId:"+userId);
        Log.i("pppppppppp","nonce:"+nonce);
        Log.i("pppppppppp","walletAddress:"+walletAddress);
        Log.i("pppppppppp","coinId:"+coinId);


        presenter.sendCoin(hex,userId,nonce,walletAddress,coinId);

        //传4个参数   hex ：合约
//        userId : 用户id
//        nonce : 随机数
//        walletAddress ：钱包地址



    }



    @OnClick(R.id.btn_send)
    public void onViewClicked() {
//        Intent intent = new Intent(this, TradeBookActivity.class);
//        intent.putExtra("result", "0000");
//        startActivity(intent);
        progressDialog = new ProgressDialog(this,"数据正在加载中... ...");
        progressDialog.show();

        sendCoin(nonce);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)//来自于SendDetailPresenter
    public void event(Event event) {
        progressDialog.dismiss();
        switch (event.code){
            case Event.Code.SuccessSendDtailCode:
                HashWapper hashWapper = (HashWapper) event.data;
                String hashId = hashWapper.getTxHash();
                BaseResponse.ResponseResult resultData = (BaseResponse.ResponseResult) event.resultData;
                Toast.makeText(this, resultData.getMsg(), Toast.LENGTH_SHORT).show();
                showSuccessDialog();
                break;
            case Event.Code.SendNonceCode://吧随机数重新设置。然后重新发送一笔交易
                HashWapper hashWapper1 = (HashWapper) event.data;
                String nonceRight = hashWapper1.getNonce();
                BaseResponse.ResponseResult resultData1 = (BaseResponse.ResponseResult) event.resultData;
                Toast.makeText(this, resultData1.getMsg()+"请把输入正确的随机数："+nonceRight, Toast.LENGTH_SHORT).show();
                showNoceDialog(nonceRight);
//                sendCoin(nonceRight);
                break;
            case Event.Code.SendNonceCode121:
                BaseResponse.ResponseResult resultData121 = (BaseResponse.ResponseResult) event.resultData;
                Toast.makeText(this, resultData121.getMsg(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,MainActivity.class));
                break;
            case Event.Code.FailSendDtailCode:
                BaseResponse.ResponseResult resultData2 = (BaseResponse.ResponseResult) event.resultData;
                Toast.makeText(this, resultData2.getMsg(), Toast.LENGTH_SHORT).show();
                break;
            case Event.Code.RequestFailsendDetailRegistCode:
                Toast.makeText(this, "连接服务失败", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    private void showNoceDialog(String nonceRight) {
        new AlertDialog.Builder(this).
                setMessage("这笔交易的随机数不正确，请在冷端发送页面填写正确的交易序号: "+nonceRight).
                setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                }).

                show();

    }


    private void showSuccessDialog() {
        new AlertDialog.Builder(this).
                setMessage("这笔交易已经发送，是否查看交易记录").
                setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(),TradeBookActivity.class));
                    }
                }).
                setNegativeButton("否",null).
                show();

    }


}
