package com.xcoinpay.pizza.pizzawallet.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xcoinpay.pizza.pizzawallet.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by llq on 2017/12/27 0027.
 * 自定义加载对话框
 */

public class ProgressDialog {
    @BindView(R.id.progress_iv)
    ImageView progress_iv;
    @BindView(R.id.progress_message)
    TextView progress_message;

    private  AnimationDrawable animationDrawable = null;
    public Dialog mDialog;

    public ProgressDialog(Context context, String message){

       View view = LayoutInflater.from(context).inflate(R.layout.progress_dialog,null);
        ButterKnife.bind(this,view);

        progress_iv.setImageResource(R.drawable.progress_animate_loading);
        animationDrawable = (AnimationDrawable) progress_iv.getDrawable();

        progress_message.setText("Loading ... ...");


        if(animationDrawable!=null){
            animationDrawable.setOneShot(false);
            animationDrawable.start();
        }

        mDialog = new Dialog(context, R.style.dialog);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);
    }

    public void show() {
        mDialog.show();
    }

    public void setCanceledOnTouchOutside(boolean cancel) {
        mDialog.setCanceledOnTouchOutside(cancel);
    }

    public void dismiss() {
        if(mDialog.isShowing()) {
            mDialog.dismiss();
            animationDrawable.stop();
        }
    }
    public boolean isShowing(){
        if(mDialog.isShowing()) {
            return true;
        }
        return false;
    }
}
