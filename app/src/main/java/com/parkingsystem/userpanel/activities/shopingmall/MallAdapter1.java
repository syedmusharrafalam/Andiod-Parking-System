package com.parkingsystem.userpanel.activities.shopingmall;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parkingsystem.R;
import com.parkingsystem.booking.Booking;

import java.util.List;

/**
 * Created by User on 4/17/2018.
 */

public class MallAdapter1 extends RecyclerView.Adapter<MallAdapter1.MyViewHolder> {
  Context context;
    List<Booking>mBooking;
    long startTime;
    long endTime;

    public MallAdapter1(Context context, List<Booking> mBooking, long startTime, long endTime) {
        this.context = context;
        this.mBooking = mBooking;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(context).inflate(R.layout.custom_button_mall_one,parent,false);
        MyViewHolder vHolder=new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mBooking.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
           TextView btnSlotMall1;
        public MyViewHolder(View itemView) {
            super(itemView);
            btnSlotMall1=(TextView)itemView.findViewById(R.id.btnSlotMall1);

        }
    }
}
