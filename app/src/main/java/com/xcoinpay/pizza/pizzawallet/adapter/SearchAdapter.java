package com.xcoinpay.pizza.pizzawallet.adapter;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.bean.Bean;
import com.xcoinpay.pizza.pizzawallet.view.TokenActivity;

import java.util.List;

/**
 * Created by llq on 2017/12/21 0021.
 */

public class SearchAdapter extends BaseAdapter {

    private  TokenActivity tokenActivity;
    public   List<Bean> beans;

    public SearchAdapter(TokenActivity tokenActivity, List<Bean> resultData) {
        this.beans = resultData;
        this.tokenActivity = tokenActivity;

    }

    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public Object getItem(int position) {
        return beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            holder = new ViewHolder();
            convertView= View.inflate(tokenActivity, R.layout.item_tiken, null);
            holder.icon = convertView.findViewById(R.id.token_iv);
            holder.name = convertView.findViewById(R.id.token_tv_name);
           holder.name1 =  convertView.findViewById(R.id.token_tv_name1);
           convertView.setTag(holder);
        }
        else holder = (ViewHolder) convertView.getTag();

        //设置数据
        holder.name.setText(beans.get(position).getTitle());
        holder.name1.setText(beans.get(position).getContent());
        holder.icon.setImageResource(beans.get(position).getIconId());

        return convertView;
    }
    static class ViewHolder{
        public ImageView icon;
        public TextView name,name1;
    }
}
