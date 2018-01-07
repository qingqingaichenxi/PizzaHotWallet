package com.xcoinpay.pizza.pizzawallet.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.contant.Contant;
import com.xcoinpay.pizza.pizzawallet.util.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity {
    @BindView(R.id.welcome_img)
    ImageView welcome_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        animate();
    }

    private void animate() {
        // 旋转动画，
        RotateAnimation rotateAnimation = new RotateAnimation(
                0, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setFillAfter(true);// 让动画停留在结束时的位置

        // 缩放动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1, 1,
                1, 1,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setFillAfter(true);

        // 渐变动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);

        // 动画集合
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(scaleAnimation);

        welcome_img.startAnimation(animationSet);

        // 监听动画
        animationSet.setAnimationListener(new MyAnimationListener());
    }
    class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            // 当动画结束后，根据是否打开过软件，判断进入引导界面还是主界面
            boolean isFirstOpen = SPUtils.getBoolean(getApplicationContext(), Contant.IS_APP_FIRST_OPEN, true);
            if(isFirstOpen){
                // 进入引导界面
                System.out.println("进入引导界面");
                startActivity(new Intent(WelcomeActivity.this,GuideActivity.class));
            }else {
                // 进入主界面
                System.out.println("进入主界面");
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
            }
            finish();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

    }
}
