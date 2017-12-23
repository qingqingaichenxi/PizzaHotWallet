package com.xcoinpay.pizza.pizzawallet.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by llq on 2017/12/23 0023.
 */

public class WelcomeAdapter extends PagerAdapter {

    private  ArrayList<ImageView> imageViews;

    public WelcomeAdapter(ArrayList<ImageView> imgs) {
        imageViews = imgs;
    }

    @Override
    public int getCount() {
        return imageViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imageViews.get(position));
        return imageViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
