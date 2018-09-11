package com.parkingsystem.userpanel.activities.resturants;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parkingsystem.R;
import com.parkingsystem.booking.Booking;

import java.util.List;

/**
 * Created by User on 4/17/2018.
 */

public class RecyclerViewAdapterThree extends RecyclerView.Adapter<RecyclerViewAdapterThree.MyViewHolder> {
  Context context;
    List<Booking>mBooking;
    long startTime;
    long endTime;

    public RecyclerViewAdapterThree(Context context, List<Booking> mBooking, long startTime, long endTime) {
        this.context = context;
        this.mBooking = mBooking;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(context).inflate(R.layout.custom_button_three,parent,false);

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




        public MyViewHolder(View itemView) {
            super(itemView);


        }
    }
}
