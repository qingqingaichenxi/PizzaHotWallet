package com.xcoinpay.pizza.pizzawallet.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.bean.BookInfo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by llq on 2017/12/27 0027.
 */

public class AddBookAdapter extends RecyclerView.Adapter<AddBookAdapter.Hodler> {

    private  ArrayList<BookInfo> bookInfos;

    public AddBookAdapter(ArrayList<BookInfo> bookInfos) {
        this.bookInfos = bookInfos;
    }

    @Override
    public Hodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addbook_item, null);
        return new Hodler(view);
    }

    @Override
    public void onBindViewHolder(Hodler holder, int position) {

        holder.coin.setText(bookInfos.get(position).getCoin()+"--");
        holder.name.setText(bookInfos.get(position).getName());
        holder.address.setText(bookInfos.get(position).getAddress());

    }

    @Override
    public int getItemCount() {
        return bookInfos.size();
    }

    public class Hodler extends RecyclerView.ViewHolder{
        @BindView(R.id.book_coin)
        TextView coin;
        @BindView(R.id.book_name)
        TextView name;
        @BindView(R.id.book_address)
        TextView address;

        public Hodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
