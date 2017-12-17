package com.xcoinpay.pizza.pizzawallet.view.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;

import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.base.BaseFragment;
import com.xcoinpay.pizza.pizzawallet.presenter.HomePresenter;
import com.xcoinpay.pizza.pizzawallet.view.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by llq on 2017/12/16 0016.
 */

public class HomeFragment extends BaseFragment<HomePresenter> {
    @BindView(R.id.ll_scan)
    LinearLayout ll_scan;
    @BindView(R.id.ll_dressbook)
    LinearLayout ll_dressbook;
    @BindView(R.id.ll_tradebook)
    LinearLayout ll_tradebook;
    @BindView(R.id.btn_barcode)
    Button btn;
    @BindView(R.id.iv)
    ImageView iv;


    @Override
    public HomePresenter getPresnter() {
        return new HomePresenter();
    }

    @Override
    public void init() {
        toolbar_title.setText("首页");
    }

    @Override
    public int getLayoutId() {
        return R.layout.homefragment;
    }

    @OnClick({R.id.ll_scan, R.id.ll_dressbook, R.id.ll_tradebook, R.id.btn_barcode})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.ll_scan:
//                Intent intent = new Intent(getActivity(), CaptureActivity.class);
//                startActivityForResult(intent, MainActivity.REQUEST_CODE);
//                IntentIntegrator.forSupportFragment(this).initiateScan();
                new IntentIntegrator(getActivity()).initiateScan();
                break;
            case R.id.ll_dressbook:
                break;
            case R.id.ll_tradebook:
                break;
            case R.id.btn_barcode:
                Bitmap img = encodeAsBitmap("fsdfgdfgf");
                iv.setImageBitmap(img);
                break;
        }
    }


    Bitmap encodeAsBitmap(String str) {
        Bitmap bitmap = null;
        BitMatrix result = null;
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            result = multiFormatWriter.encode(str, BarcodeFormat.QR_CODE, 200, 200);
            // 使用 ZXing Android Embedded 要写的代码
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            bitmap = barcodeEncoder.createBitmap(result);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException iae) { // ?
            return null;
        }
        return bitmap;
    }

}
